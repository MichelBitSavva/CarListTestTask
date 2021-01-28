package com.test.automobile.model.error

data class ErrorJson(
    val code: Int,
    val errors: List<Error>,
    val message: String
)