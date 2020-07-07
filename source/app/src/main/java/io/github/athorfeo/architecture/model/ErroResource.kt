package io.github.athorfeo.architecture.model

data class ErrorResource(
    val code: Int?,
    val message: String? = null
)