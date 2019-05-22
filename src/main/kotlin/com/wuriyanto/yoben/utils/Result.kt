package com.wuriyanto.yoben.utils

sealed class Result<T, E>

data class Ok<T, E>(val value: T): Result<T, E>()

data class Error<T, E>(val value: E): Result<T, E>()

data class ErrorMessage(val message: String)