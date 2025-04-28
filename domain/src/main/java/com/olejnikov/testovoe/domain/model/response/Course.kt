package com.olejnikov.testovoe.domain.model.response

import kotlinx.serialization.SerialName


@SerialName("courses")
data class Courses (
    val courses: List<Course>
)

data class Course(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String?,
    @SerialName("text")
    val text: String?,
    @SerialName("price")
    val price: String?,
    @SerialName("rate")
    val rate: String?,
    @SerialName("startDate")
    val startDate: String?,
    @SerialName("hasLike")
    val hasLike: Boolean?,
    @SerialName("publishDate")
    val publishDate: String?,
)
