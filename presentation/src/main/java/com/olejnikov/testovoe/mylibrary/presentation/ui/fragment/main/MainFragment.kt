package com.olejnikov.testovoe.mylibrary.presentation.ui.fragment.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.asLiveData
import com.olejnikov.testovoe.domain.model.response.Course
import com.olejnikov.testovoe.mylibrary.presentation.databinding.FragmentMainBinding
import com.olejnikov.testovoe.mylibrary.presentation.ui.fragment.BaseFragment
import com.olejnikov.testovoe.mylibrary.presentation.ui.fragment.main.adapter.CourseAdapter
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.Screens
import com.olejnikov.testovoe.mylibrary.presentation.viewModel.main.MainVM
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<Screens.Main, FragmentMainBinding>(
    FragmentMainBinding::inflate,
    Screens.Main
) {

    private val adapter = CourseAdapter()
    private val vm: MainVM by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpSortedListener()

        vm.error.asLiveData().observe(viewLifecycleOwner, ::onError)
        vm.courses.observe(viewLifecycleOwner, ::setUpCourses)

        scope.launch { vm.loadAllCourses() }
    }

    private fun setUpAdapter() {
        binding.recyclerCourses.adapter = adapter
        adapter.setUpListenerReadMore {}
        adapter.setUpListenerAddFavourite(::addFavourite)
    }

    private fun setUpSortedListener() {
        binding.sortedBy.setOnClickListener {
            val courses = vm.courses.value
            if(!courses.isNullOrEmpty()) setUpCourses(courses.sortedBy { it.publishDate })
        }
    }

    private fun setUpCourses(list: List<Course>?) {
        when(list.isNullOrEmpty()) {
            true -> {}
            else -> adapter.setData(list)
        }
    }

    private fun addFavourite(id: Int) = vm.addFavourite (id, ::setUpCourses)


}