package com.olejnikov.testovoe.mylibrary.presentation.ui.view.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import com.olejnikov.testovoe.mylibrary.presentation.R
import com.olejnikov.testovoe.mylibrary.presentation.databinding.ViewButtonBinding
import com.olejnikov.testovoe.mylibrary.presentation.ui.view.CustomBindingView

class ButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CustomBindingView<ViewButtonBinding>(context, attrs, defStyleAttr) {


    override fun inflateBinding(i: LayoutInflater, p: ViewGroup) = ViewButtonBinding.inflate(i, p, true)

    override fun applyAttrs(attrs: AttributeSet?) {
        super.applyAttrs(attrs)
        context.theme.obtainStyledAttributes(attrs, R.styleable.ButtonView, 0, 0).apply {
            setButtonName(getString(R.styleable.ButtonView_titleText))
        }
    }

    fun setButtonName(text: String?) {
        binding.button.text = text
    }




}