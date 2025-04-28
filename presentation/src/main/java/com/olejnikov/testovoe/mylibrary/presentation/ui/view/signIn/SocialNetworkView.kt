package com.olejnikov.testovoe.mylibrary.presentation.ui.view.signIn

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import com.olejnikov.testovoe.mylibrary.presentation.databinding.ViewSocialNetworkBinding
import com.olejnikov.testovoe.mylibrary.presentation.ui.view.CustomBindingView

class SocialNetworkView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CustomBindingView<ViewSocialNetworkBinding>(context, attrs, defStyleAttr) {

    override fun inflateBinding(i: LayoutInflater, p: ViewGroup) = ViewSocialNetworkBinding.inflate(i, p, true)


    fun onClickVk(onClick:() -> Unit) {
        binding.vk.setOnClickListener {
            onClick.invoke()
        }
    }

    fun onClickClassmate(onClick:() -> Unit) {
        binding.classmate.setOnClickListener {
            onClick.invoke()
        }
    }





}