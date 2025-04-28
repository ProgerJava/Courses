package com.olejnikov.testovoe.data.gateway

import android.util.Log
import com.olejnikov.testovoe.data.global.tag
import com.olejnikov.testovoe.domain.exception.ApiError
import com.olejnikov.testovoe.domain.exception.ApiErrorException
import com.olejnikov.testovoe.domain.exception.EmptyException
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException


class Mappers(private val retrofit: Retrofit) {

    fun <T> data(response: Response<T>): T {
        return if (response.isSuccessful) {
            val body = response.body()
            when(body != null && response.errorBody() == null) {
                true -> return body
                else -> {
                    var error = getErrorBodyAs(response, ApiError::class.java)
                    if (error != null) {
                        error = error.copy(status = response.code())
                        throw ApiErrorException(error)
                    }
                    else {
                        throw RuntimeException("get error in getErrorBodyAs()")
                    }
                }
            }
        } else throw EmptyException()
    }

    private fun <T> getErrorBodyAs(response: Response<*>, type: Class<T>): T? {
        val body = response.errorBody() ?: throw IOException("no body in response")
        val converter: Converter<ResponseBody, T> =
            retrofit.responseBodyConverter(type, arrayOfNulls<Annotation>(0))
        var error: T? = null
        try {
            error = converter.convert(body)
        } catch (e: IOException) {
            Log.w(tag(),
                response.raw().request.method + " " + response.raw().request.url.toString(),
                e
            )
        }
        return error
    }

}

