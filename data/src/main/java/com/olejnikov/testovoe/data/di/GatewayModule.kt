package com.olejnikov.testovoe.data.di

import com.olejnikov.testovoe.data.gateway.MainGateway
import org.koin.dsl.module

val gateModule = module {
    single<MainGateway> { MainGateway(get(), get()) }

}