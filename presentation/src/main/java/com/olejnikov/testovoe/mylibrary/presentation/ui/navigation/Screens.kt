package com.olejnikov.testovoe.mylibrary.presentation.ui.navigation

import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.behavior.ChildBehavior
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.behavior.FragmentBehavior
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.behavior.MainBehavior
import kotlinx.serialization.Serializable

interface Screen {
    val behavior: FragmentBehavior
}


@Serializable
sealed class Screens: Screen {

    @Serializable
    data object Splash : Screens() {
        override val behavior: FragmentBehavior get() = ChildBehavior()
    }

    @Serializable
    data object SignIn : Screens() {
        override val behavior: FragmentBehavior get() = ChildBehavior()
    }

    @Serializable
    data object Main : Screens() {
        override val behavior: FragmentBehavior get() = MainBehavior()
    }

}

