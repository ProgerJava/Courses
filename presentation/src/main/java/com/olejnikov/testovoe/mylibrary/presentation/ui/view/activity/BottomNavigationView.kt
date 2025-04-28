package com.olejnikov.testovoe.mylibrary.presentation.ui.view.activity

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import com.olejnikov.testovoe.mylibrary.presentation.R
import com.olejnikov.testovoe.mylibrary.presentation.databinding.ViewBottomNavigationBinding
import com.olejnikov.testovoe.mylibrary.presentation.ui.view.CustomBindingView

class BottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CustomBindingView<ViewBottomNavigationBinding>(context, attrs, defStyleAttr) {


    override fun inflateBinding(i: LayoutInflater, p: ViewGroup) = ViewBottomNavigationBinding.inflate(i, p, true)

    init {
        updateDesign(binding.main)
    }


    fun setOnItemClickListener(item: (id: Int) -> Unit) = with(binding) {
        main.setOnClickListener {
            updateDesign(main)
            item(R.id.main)
        }
        favourite.setOnClickListener {
            updateDesign(favourite)
            item(R.id.favourite)
        }
        profile.setOnClickListener {
            updateDesign(profile)
            item(R.id.profile)
        }
    }

    private fun updateDesign(ic: ItemNavigationBarView) {
        clearAll()
        ic.setUpImageTint(R.color.appGreen)
        ic.setUpTextColor(R.color.appGreen)
        ic.needToShowBack(true)

    }

    private fun clearAll() = with(binding) {
        main.apply {
            setUpImageTint(R.color.appWhite)
            setUpTextColor(R.color.appWhite)
            needToShowBack(false)
        }

        favourite.apply {
            setUpImageTint(R.color.appWhite)
            setUpTextColor(R.color.appWhite)
            needToShowBack(false)
        }

        profile.apply {
            setUpImageTint(R.color.appWhite)
            setUpTextColor(R.color.appWhite)
            needToShowBack(false)
        }

    }





}