package com.app.ecommerce.utils

data class ResponseStatus<out T>(
    val status: Status,
    val code: Int,
    val message: String? = null,
    val body: T? = null
) {

    enum class Status {
        SUCCESS,
        ERROR
    }

}