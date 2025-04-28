package com.olejnikov.testovoe.data.di

import com.olejnikov.testovoe.data.Constants
import com.olejnikov.testovoe.data.gateway.Mappers
import com.olejnikov.testovoe.data.network.api.MainApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    // retrofit interfaces
    single { Mappers(get(named(Constants.RETROFIT))) }

    single<MainApi> { get<Retrofit>(named(Constants.RETROFIT)).create(MainApi::class.java) }

}