package com.olejnikov.testovoe.mylibrary.presentation.ui.baseRecyclerLogic

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.olejnikov.testovoe.mylibrary.presentation.ui.view.CustomListView

abstract class BaseRVAdapter<M, V: CustomListView<M, *>>: RecyclerView.Adapter<BaseVH<M, V>>() {

    private var items: MutableList<M> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<M, V> {
        val view = getItemView(parent.context, viewType)
        return BaseVH(view)
    }

    override fun onBindViewHolder(holder: BaseVH<M, V>, position: Int) {
        val model = getItem(position)
        holder.apply {
            bindItem(model)
        }
    }

    override fun getItemCount() = getData().size

    protected fun submitList(newList: List<M>, detectMoves: Boolean = true) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = items.size
            override fun getNewListSize() = newList.size
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = items[oldItemPosition]
                val newItem = newList[newItemPosition]
                return this@BaseRVAdapter.areItemsTheSame(oldItem, newItem)
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = items[oldItemPosition]
                val newItem = newList[newItemPosition]
                return this@BaseRVAdapter.areContentsTheSame(oldItem, newItem)
            }
        }, detectMoves)

        items.clear()
        items.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    // Методы для сравнения элементов (должны быть реализованы в наследниках)
    protected abstract fun areItemsTheSame(old: M, new: M): Boolean
    protected abstract fun areContentsTheSame(old: M, new: M): Boolean


    fun getData(): MutableList<M> = items

    fun getItem(position: Int): M = items[position]

    abstract fun getItemView(context: Context, viewType: Int): V


}