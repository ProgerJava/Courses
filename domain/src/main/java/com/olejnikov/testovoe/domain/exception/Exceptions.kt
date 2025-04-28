package com.olejnikov.testovoe.domain.exception


data class ApiError(
    val status: Int = -1,
    var code: Int = 0,
    var message: String = "Error",
    var description: String = "",
)

class ApiErrorException(val error: ApiError) : RuntimeException(error.message)

class EmptyException : RuntimeException()