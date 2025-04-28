package com.olejnikov.testovoe.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.olejnikov.testovoe.data.Constants
import com.olejnikov.testovoe.data.store.UserDataStoreImpl
import com.olejnikov.testovoe.domain.store.UserDataStore
import org.koin.core.qualifier.named
import org.koin.dsl.module

val storeModule = module {

    single<Gson>(named(Constants.DEFAULT_GSON)) {GsonBuilder().create()}
    single<UserDataStore> { UserDataStoreImpl(get(), get(named(Constants.DEFAULT_GSON))) }

}
