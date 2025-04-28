package com.olejnikov.testovoe.mylibrary.presentation.ui.view.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import com.olejnikov.testovoe.mylibrary.presentation.databinding.ViewSearcherBinding
import com.olejnikov.testovoe.mylibrary.presentation.ui.view.CustomBindingView

class SearcherView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CustomBindingView<ViewSearcherBinding>(context, attrs, defStyleAttr) {


    override fun inflateBinding(i: LayoutInflater, p: ViewGroup) = ViewSearcherBinding.inflate(i, p, true)







}