package com.olejnikov.testovoe.mylibrary.presentation.ui.view.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import com.olejnikov.testovoe.mylibrary.presentation.R
import com.olejnikov.testovoe.mylibrary.presentation.databinding.ViewTitleBinding
import com.olejnikov.testovoe.mylibrary.presentation.ui.view.CustomBindingView

class TitleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CustomBindingView<ViewTitleBinding>(context, attrs, defStyleAttr) {


    override fun inflateBinding(i: LayoutInflater, p: ViewGroup) = ViewTitleBinding.inflate(i, p, true)

    override fun applyAttrs(attrs: AttributeSet?) {
        super.applyAttrs(attrs)
        context.theme.obtainStyledAttributes(attrs, R.styleable.TitleView, 0, 0).apply {
            setTitleText(getString(R.styleable.TitleView_titleText))
        }
    }

    fun setTitleText(text: String?) {
        binding.title.text = text
    }




}