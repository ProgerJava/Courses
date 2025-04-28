package com.olejnikov.testovoe.mylibrary.presentation.ui.baseRecyclerLogic

import androidx.recyclerview.widget.RecyclerView
import com.olejnikov.testovoe.mylibrary.presentation.ui.view.CustomListView


open class BaseVH<M, V : CustomListView<M, *>>(protected val view: V) :
    RecyclerView.ViewHolder(view) {

    fun getItemView() = view

    fun bindItem(model: M) = view.setData(model)


}