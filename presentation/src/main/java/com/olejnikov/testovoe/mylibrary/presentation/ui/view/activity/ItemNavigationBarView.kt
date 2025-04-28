package com.olejnikov.testovoe.mylibrary.presentation.ui.view.activity

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import com.olejnikov.testovoe.mylibrary.presentation.R
import com.olejnikov.testovoe.mylibrary.presentation.databinding.ViewItemNavigationBarBinding
import com.olejnikov.testovoe.mylibrary.presentation.ui.view.CustomBindingView

class ItemNavigationBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CustomBindingView<ViewItemNavigationBarBinding>(context, attrs, defStyleAttr) {


    override fun inflateBinding(i: LayoutInflater, p: ViewGroup) = ViewItemNavigationBarBinding.inflate(i, p, true)

    override fun applyAttrs(attrs: AttributeSet?) {
        super.applyAttrs(attrs)
        context.theme.obtainStyledAttributes(attrs, R.styleable.ItemNavigationBarView, 0, 0).apply {
            setUpTitle(getString(R.styleable.ItemNavigationBarView_titleText))
            setUpImage(getDrawable(R.styleable.ItemNavigationBarView_image))
        }
    }

    fun setUpTitle(text: String?) {
        binding.text.text = text
    }

    fun setUpImage(drawable: Drawable?) {
        binding.icon.setImageDrawable(drawable)
    }

    fun setUpImageTint(color: Int) {
        binding.icon.imageTintList = context.getColorStateList(color)
    }

    fun needToShowBack(needToShow: Boolean) = with(binding.back) {
        backgroundTintList = when(needToShow) {
            true -> context.getColorStateList(R.color.lightGray)
            false -> context.getColorStateList(android.R.color.transparent)
        }
    }

    fun setUpTextColor(color: Int) {
        binding.text.setTextColor(context.getColor(color))
    }




}