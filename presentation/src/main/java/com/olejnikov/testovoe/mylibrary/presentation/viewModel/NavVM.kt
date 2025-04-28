package com.olejnikov.testovoe.mylibrary.presentation.viewModel

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.Navigate
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.Screens
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.behavior.FragmentBehavior
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class NavVM: BaseVM() {


    private val _navigation = Channel<Navigate>()
    val navigation = _navigation.receiveAsFlow()

    private val _behavior = Channel<FragmentBehavior>()
    val behavior = _behavior.receiveAsFlow()

    fun postBehavior(_behavior: FragmentBehavior) {
        viewModelScope.launch {
            this@NavVM._behavior.send(_behavior)
        }
    }

    fun forward(
        screen: Screens,
        options: NavOptions? = null
    ) {
        viewModelScope.launch {
            _navigation.send(Navigate.Forward(screen, options))
        }
    }

    fun back() {
        viewModelScope.launch {
            _navigation.send(Navigate.Back)
        }
    }

}