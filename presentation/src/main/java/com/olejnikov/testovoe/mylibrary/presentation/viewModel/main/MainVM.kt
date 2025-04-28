package com.olejnikov.testovoe.mylibrary.presentation.viewModel.main

import androidx.lifecycle.MutableLiveData
import com.olejnikov.testovoe.domain.model.response.Course
import com.olejnikov.testovoe.domain.repository.MainRepository
import com.olejnikov.testovoe.domain.store.UserDataStore
import com.olejnikov.testovoe.mylibrary.presentation.viewModel.BaseVM

class MainVM(private val repository: MainRepository, private val store: UserDataStore) : BaseVM() {

    val courses = MutableLiveData<List<Course>?>()

    suspend fun loadAllCourses()  {
        val storeCourses = store.getCourses()?.courses
        courses.value = storeCourses
        val serverCourses = repository.loadAllCourses()?.courses
        if(storeCourses != serverCourses)
            courses.value = repository.loadAllCourses()?.courses
    }

    fun addFavourite(id: Int, onComplete:(List<Course>?) -> Unit) {
        val favourites = store.getFavourites()?.toMutableList() ?: mutableListOf()

        when(favourites.contains(id as Integer)) {
            true -> favourites.remove(id)
            else -> favourites.add(id)
        }
        store.addFavourites(favourites)
        onComplete.invoke(courses.value)
    }


}