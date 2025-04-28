package com.olejnikov.testovoe.data.di


import com.olejnikov.testovoe.data.BuildConfig
import com.olejnikov.testovoe.data.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.apply


val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single(named(Constants.RETROFIT_BUILDER)) {
        Retrofit.Builder()
    }

    single (named(Constants.RETROFIT)) {
        get<Retrofit.Builder>(named(Constants.RETROFIT_BUILDER))
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

}
