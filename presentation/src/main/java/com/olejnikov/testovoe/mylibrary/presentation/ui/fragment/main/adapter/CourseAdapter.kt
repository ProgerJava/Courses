package com.olejnikov.testovoe.mylibrary.presentation.ui.fragment.main.adapter

import android.content.Context
import com.olejnikov.testovoe.domain.model.response.Course
import com.olejnikov.testovoe.mylibrary.presentation.ui.baseRecyclerLogic.BaseRVAdapter
import com.olejnikov.testovoe.mylibrary.presentation.ui.baseRecyclerLogic.BaseVH
import com.olejnikov.testovoe.mylibrary.presentation.ui.view.recyclerItem.ViewItemCourse

class CourseAdapter: BaseRVAdapter<Course, ViewItemCourse>() {

    private var readMoreListener:(id: Int) -> Unit = {}
    private var addFavouriteListener:(id: Int) -> Unit = {}

    fun setData(list: List<Course>) = submitList(list)

    override fun areItemsTheSame(old: Course, new: Course) = old.id == new.id

    override fun areContentsTheSame(old: Course, new: Course) =
        old.title == new.title && old.text == new.text

    override fun getItemView(context: Context, viewType: Int) = ViewItemCourse(context)

    override fun onBindViewHolder(holder: BaseVH<Course, ViewItemCourse>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.getItemView().setUpListenerAddFavourite {
            addFavouriteListener.invoke(it)
        }
        holder.getItemView().setUpListenerReadMore {
            readMoreListener.invoke(it)
        }
    }


    fun setUpListenerReadMore(onClick:(id: Int) -> Unit) {
        readMoreListener = onClick
    }

    fun setUpListenerAddFavourite(onClick:(id: Int) -> Unit) {
        addFavouriteListener = onClick
    }
}