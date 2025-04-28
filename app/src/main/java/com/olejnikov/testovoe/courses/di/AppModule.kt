package com.olejnikov.testovoe.courses.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val appModule = module {
    factory { androidContext().resources }
}