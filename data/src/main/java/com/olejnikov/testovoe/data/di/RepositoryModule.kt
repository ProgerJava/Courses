package com.olejnikov.testovoe.data.di

import com.olejnikov.testovoe.data.repositoryImpl.MainRepositoryImpl
import com.olejnikov.testovoe.domain.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MainRepository> { MainRepositoryImpl(get(), get()) }

}