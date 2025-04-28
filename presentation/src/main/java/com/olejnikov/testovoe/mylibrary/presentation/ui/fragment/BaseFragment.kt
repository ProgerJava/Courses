package com.olejnikov.testovoe.mylibrary.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.olejnikov.testovoe.domain.exception.ApiErrorException
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.Screen
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.Screens
import com.olejnikov.testovoe.mylibrary.presentation.viewModel.NavVM
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.net.ConnectException
import java.net.UnknownHostException
import kotlin.getValue
import kotlin.jvm.java
import kotlin.toString

abstract class BaseFragment<S : Screen, B : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B,
    private val screen: S
) : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!
    protected val navVm: NavVM by activityViewModel()
    private lateinit var coroutineExceptionHandler: CoroutineExceptionHandler
    protected lateinit var scope: CoroutineScope

    //protected val gson: Gson by inject(named(Constants.DEFAULT_GSON))


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(tag(), throwable.message.toString())
        }
        scope = CoroutineScope(Dispatchers.Main + coroutineExceptionHandler)


        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        navVm.postBehavior(screen.behavior)
    }


    protected open fun onError(t: Throwable) {
        Log.w(tag(), t.message, t)
        when (t) {
            is ApiErrorException -> onApiException(t)
            is UnknownHostException -> onOtherException(Exception("Нет подключения к интернету, попробуйте позже"))
            is ConnectException -> onOtherException(Exception("Не удалось подключиться к серверу"))
            else -> onOtherException(t)
        }
    }

    protected open fun onApiException(t: ApiErrorException) {
        onOtherException(t)
        Log.e(tag(), "${t.message}\n${t.error.message}\n${t.error.description}")
    }

    protected open fun onOtherException(t: Throwable) {
        val ab = AlertDialog.Builder(requireContext())
        ab.setTitle("Error")
        ab.setMessage(t.message ?: "Unknown error")
        ab.setPositiveButton("Ok") { d, _ ->
            d.dismiss()
        }
        ab.show()
    }

    protected fun forward(s: Screens) = navVm.forward(s)


    protected fun tag(): String = this::class.java.name

}