package com.olejnikov.testovoe.mylibrary.presentation.viewModel.splash

import com.olejnikov.testovoe.domain.store.UserDataStore
import com.olejnikov.testovoe.mylibrary.presentation.viewModel.BaseVM

class SplashVM(private val store: UserDataStore): BaseVM() {

    fun isThatFirstLaunch(): Boolean {
        when(store.isThatFirstLaunch()) {
            true -> {
                store.registerUserEnter()
                return true
            }
            else -> return false
        }
    }

}