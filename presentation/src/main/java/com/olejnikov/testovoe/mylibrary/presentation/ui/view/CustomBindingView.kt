package com.olejnikov.testovoe.mylibrary.presentation.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class CustomBindingView<B : ViewBinding> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(
    context,
    attrs,
    defStyleAttr
) {
    protected lateinit var binding: B

    init {
        buildUI(attrs)
    }


    protected open fun applyAttrs(attrs: AttributeSet?) {
        if (attrs == null) return
    }

    protected abstract fun inflateBinding(i: LayoutInflater, p: ViewGroup): B


    private fun buildUI(attrs: AttributeSet?) {
        binding = inflateBinding(LayoutInflater.from(context), this)
        applyAttrs(attrs)
    }
}
