package com.olejnikov.testovoe.data.global


import com.olejnikov.testovoe.data.gateway.Mappers
import retrofit2.Response



fun <T : Any> T.tag() = this.javaClass.toString()

fun <T> Response<T?>.map(mappers: Mappers): T? {
    return mappers.data(this)
}


