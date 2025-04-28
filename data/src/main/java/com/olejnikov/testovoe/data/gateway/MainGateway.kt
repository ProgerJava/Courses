package com.olejnikov.testovoe.data.gateway

import com.olejnikov.testovoe.data.global.map
import com.olejnikov.testovoe.data.network.api.MainApi
import com.olejnikov.testovoe.domain.model.response.Courses


class MainGateway(private val mainApi: MainApi, private val mappers: Mappers) {

    suspend fun loadAllCourses(): Courses? = mainApi.loadAllCourses().map(mappers)
}