package com.olejnikov.testovoe.mylibrary.presentation.ui.fragment.splash

import android.os.Bundle
import android.view.View
import com.olejnikov.testovoe.mylibrary.presentation.databinding.FragmentSplashBinding
import com.olejnikov.testovoe.mylibrary.presentation.global.clickListener
import com.olejnikov.testovoe.mylibrary.presentation.global.toScreen
import com.olejnikov.testovoe.mylibrary.presentation.ui.fragment.BaseFragment
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.Screens
import com.olejnikov.testovoe.mylibrary.presentation.viewModel.splash.SplashVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<Screens.Splash, FragmentSplashBinding>(
    FragmentSplashBinding::inflate,
    Screens.Splash
) {

    private val vm: SplashVM by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(vm.isThatFirstLaunch()) {
            true -> setUpListeners()
            else -> navVm.forward(Screens.SignIn)
        }
    }


    private fun setUpListeners() = with(binding) {
        continueButton.clickListener(::forward.toScreen(Screens.SignIn))
    }

}