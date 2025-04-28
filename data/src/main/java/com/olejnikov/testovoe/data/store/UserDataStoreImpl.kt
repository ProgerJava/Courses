package com.olejnikov.testovoe.data.store

import android.content.Context
import com.google.gson.Gson
import com.olejnikov.testovoe.domain.model.response.Course
import com.olejnikov.testovoe.domain.model.response.Courses
import com.olejnikov.testovoe.domain.store.UserDataStore

class UserDataStoreImpl(context: Context, gson: Gson) : SimpleDataStore(NAME, context, gson), UserDataStore  {


    override fun registerUserEnter() = put(FLAG_FIRST_ENTER, true)
    override fun isThatFirstLaunch(): Boolean =
        get(FLAG_FIRST_ENTER, Boolean::class.java) == false ||
                get(FLAG_FIRST_ENTER, Boolean::class.java) == null

    override fun addFavourites(coursesId: List<Integer>) = put(FAVORITES_COURSES, coursesId)
    override fun getFavourites(): List<Integer>? = getList(FAVORITES_COURSES, Integer::class.java)

    override fun addCourses(courses: Courses) = put(COURSES, courses)
    override fun getCourses(): Courses?  = get(COURSES, Courses::class.java)


    override fun clearStoredData() = clear()

    companion object {
        const val NAME = "user"
        const val FLAG_FIRST_ENTER = "flag_first_enter"
        const val FAVORITES_COURSES = "favorites_courses"
        const val COURSES = "courses"


    }
}
