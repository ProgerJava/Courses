package com.olejnikov.testovoe.data.network.api

import com.olejnikov.testovoe.domain.model.response.Courses
import retrofit2.Response
import retrofit2.http.GET


interface MainApi {

    @GET("/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun loadAllCourses(): Response<Courses?>

}