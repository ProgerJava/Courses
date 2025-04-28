package com.olejnikov.testovoe.mylibrary.presentation.di


import com.olejnikov.testovoe.mylibrary.presentation.viewModel.NavVM
import com.olejnikov.testovoe.mylibrary.presentation.viewModel.main.MainVM
import com.olejnikov.testovoe.mylibrary.presentation.viewModel.splash.SplashVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val VMModule = module {
    viewModel { NavVM() }
    viewModel { SplashVM(get()) }
    viewModel { MainVM(get(), get()) }
}
