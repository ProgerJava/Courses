package com.olejnikov.testovoe.mylibrary.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.olejnikov.testovoe.mylibrary.presentation.R
import com.olejnikov.testovoe.mylibrary.presentation.databinding.ActivityMainBinding
import com.olejnikov.testovoe.mylibrary.presentation.global.gone
import com.olejnikov.testovoe.mylibrary.presentation.global.visible
import com.olejnikov.testovoe.mylibrary.presentation.ui.fragment.main.MainFragment
import com.olejnikov.testovoe.mylibrary.presentation.ui.fragment.signIn.SignInFragment
import com.olejnikov.testovoe.mylibrary.presentation.ui.fragment.splash.SplashFragment
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.Navigate
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.Screens
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.behavior.ChildBehavior
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.behavior.FragmentBehavior
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.behavior.MainBehavior
import com.olejnikov.testovoe.mylibrary.presentation.viewModel.NavVM
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class MainActivity : AppCompatActivity() {

    private val navVm: NavVM by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnItemBottomNavClickListener()
        initGraph()
        with(lifecycleScope) {
            launch { navVm.navigation.collect(::navigateTo) }
            launch { navVm.behavior.collect(::setUpBottomNavView) }
        }
    }

    private fun initGraph() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        navController.graph = navController.createGraph(
            startDestination = Screens.Splash
        ) {
            fragment<SplashFragment, Screens.Splash> ()
            fragment<SignInFragment, Screens.SignIn> ()
            fragment<MainFragment, Screens.Main> ()
        }
    }

    private fun navigateTo(nav: Navigate) {
        when (nav) {
            is Navigate.Forward -> navController.navigate(nav.screen, nav.options)
            is Navigate.Back -> navController.navigateUp()
        }
    }

    private fun setUpBottomNavView(behavior: FragmentBehavior) = with(binding) {
        when (behavior) {
            is ChildBehavior -> navView.gone()
            is MainBehavior -> navView.visible()
        }
    }

    private fun setOnItemBottomNavClickListener() {
        binding.navView.setOnItemClickListener { id ->
            when(id) {
                R.id.main -> navVm.forward(Screens.Main)
                R.id.favourite -> {/*TODO какой-то экран*/}
                R.id.profile -> {/*TODO какой-то экран*/}
            }
        }
    }
}