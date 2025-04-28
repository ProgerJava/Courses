package com.olejnikov.testovoe.mylibrary.presentation.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class CustomListView<M, B: ViewBinding> (context: Context, attrs: AttributeSet? = null) : CustomBindingView<B>(context, attrs) {



    abstract fun setData(model: M)

    protected fun setMatchWrap() {
        layoutParams = ViewGroup.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
    }

    protected fun setWrapWrap() {
        layoutParams = ViewGroup.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
    }

    protected fun setMatchMatch() {
        layoutParams = ViewGroup.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
    }

}