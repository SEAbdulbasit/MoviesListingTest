package com.example.swvlmobilechallenge.apiservices

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.swvlmobilechallenge.App
import com.example.swvlmobilechallenge.NO_INTERNET_CONNECTION
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

//
// Created by Abdul Basit on 11/6/2020.
//


open class ConnectivityInterceptor : Interceptor {

    private val isConnected: Boolean
        get() {
            return isInternetAvailable(App.getInstance())
        }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (!isConnected) {
            throw NoNetworkException()
        }
        return chain.proceed(originalRequest)
    }

    class NoNetworkException internal constructor() : IOException(NO_INTERNET_CONNECTION)

    companion object {
        // to check if internet is available or not
        fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities =
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    connectivityManager.activeNetwork ?: return false
                } else {
                    return false
                }
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
    }
}
