package com.olejnikov.testovoe.mylibrary.presentation.ui.navigation

import androidx.navigation.NavOptions

sealed class Navigate {
    data class Forward(
        val screen: Screens,
        val options: NavOptions? = null
    ) : Navigate()

    data object Back : Navigate()
}