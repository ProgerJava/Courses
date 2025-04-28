package com.olejnikov.testovoe.mylibrary.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olejnikov.testovoe.mylibrary.presentation.global.tag
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlin.also

open class BaseVM : ViewModel() {
    private val _error = MutableSharedFlow<Throwable>()

    private val handler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }


    val error: SharedFlow<Throwable> = _error


    protected open fun handleError(t: Throwable) = viewModelScope.launch {
        _error.emit(t).also { Log.w(tag(), t) }
    }

}