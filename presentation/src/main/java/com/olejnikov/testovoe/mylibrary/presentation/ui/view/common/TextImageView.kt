package com.olejnikov.testovoe.mylibrary.presentation.ui.view.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import com.olejnikov.testovoe.mylibrary.presentation.R
import com.olejnikov.testovoe.mylibrary.presentation.databinding.ViewTextImageBinding
import com.olejnikov.testovoe.mylibrary.presentation.ui.view.CustomBindingView

class TextImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CustomBindingView<ViewTextImageBinding>(context, attrs, defStyleAttr) {


    override fun inflateBinding(i: LayoutInflater, p: ViewGroup) = ViewTextImageBinding.inflate(i, p, true)

    override fun applyAttrs(attrs: AttributeSet?) {
        super.applyAttrs(attrs)
        context.theme.obtainStyledAttributes(attrs, R.styleable.TextImageView, 0, 0).apply {
            setButtonName(getString(R.styleable.TextImageView_titleText))
            setUpImage(getDrawable(R.styleable.TextImageView_image))
        }
    }

    fun setButtonName(text: String?) {
        binding.text.text = text
    }

    fun setUpImage(drawable: Drawable?) {
        binding.image.setImageDrawable(drawable)
    }


}