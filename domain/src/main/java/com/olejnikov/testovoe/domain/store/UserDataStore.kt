package com.olejnikov.testovoe.domain.store

import com.olejnikov.testovoe.domain.model.response.Courses

interface UserDataStore {

    fun clearStoredData()

    fun isThatFirstLaunch(): Boolean

    fun registerUserEnter()

    fun addFavourites(coursesId: List<Integer>)

    fun getFavourites(): List<Integer>?

    fun addCourses(coursesId: Courses)

    fun getCourses(): Courses?

}