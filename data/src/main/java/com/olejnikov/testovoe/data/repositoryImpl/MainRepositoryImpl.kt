package com.olejnikov.testovoe.data.repositoryImpl

import com.olejnikov.testovoe.data.gateway.MainGateway
import com.olejnikov.testovoe.domain.model.response.Courses
import com.olejnikov.testovoe.domain.repository.MainRepository
import com.olejnikov.testovoe.domain.store.UserDataStore

class MainRepositoryImpl(private val mainGateway: MainGateway, private val userDataStore: UserDataStore): MainRepository {

    override suspend fun loadAllCourses(): Courses? {
        mainGateway.loadAllCourses()?.let {
            userDataStore.addCourses(it)
        }
        return mainGateway.loadAllCourses()
    }

}