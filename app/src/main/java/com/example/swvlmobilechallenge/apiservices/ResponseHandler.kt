package com.example.swvlmobilechallenge.apiservices

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

//
// Created by Abdul Basit on 11/6/2020.
//


open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T?, responseCode: Int): Resource<T> {
        return Resource.success(data, responseCode)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(e.localizedMessage ?: "", null, -5)
            is ConnectivityInterceptor.NoNetworkException -> Resource.noInternetConnection(
                "No internet connection",
                null,
                -1
            )
            is UnknownHostException -> Resource.noInternetConnection(
                "Unknown host",
                null,
                -2
            )
            is ConnectException -> Resource.noInternetConnection(
                "No internet connection",
                null,
                -3
            )
            is SocketTimeoutException -> Resource.error(
                "Socket timeout",
                null,
                -4
            )
            else -> Resource.error("Something went wrong", null, -1)
        }
    }

    fun <T : Any> handleException(message: String): Resource<T> {
        return Resource.error(message, null, -1)
    }

    fun <T : Any> handleException(statusCode: Int): Resource<T> {
        return Resource.error("Exception occurred", null, statusCode)
    }
}
