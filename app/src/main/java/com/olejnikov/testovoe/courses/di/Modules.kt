package com.olejnikov.testovoe.courses.di

import com.olejnikov.testovoe.data.di.apiModule
import com.olejnikov.testovoe.data.di.gateModule
import com.olejnikov.testovoe.data.di.networkModule
import com.olejnikov.testovoe.data.di.repositoryModule
import com.olejnikov.testovoe.data.di.storeModule
import com.olejnikov.testovoe.mylibrary.presentation.di.VMModule

val appComponent = listOf(
    appModule,
    storeModule,
    VMModule,
    networkModule,
    apiModule,
    gateModule,
    repositoryModule
)
