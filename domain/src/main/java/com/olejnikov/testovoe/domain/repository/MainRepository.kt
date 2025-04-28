package com.olejnikov.testovoe.domain.repository

import com.olejnikov.testovoe.domain.model.response.Courses

interface MainRepository {

    suspend fun loadAllCourses(): Courses?

}