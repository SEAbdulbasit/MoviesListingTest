package com.example.swvlmobilechallenge.apiservices

import com.example.swvlmobilechallenge.BASE_URL
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

//
// Created by Abdul Basit on 11/6/2020.
//

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private fun makeHttpClient(): OkHttpClient {
    val okBuilder = OkHttpClient.Builder()
    okBuilder.connectTimeout(30, TimeUnit.SECONDS)
    okBuilder.readTimeout(30, TimeUnit.SECONDS)
    okBuilder.addInterceptor(loggingInterceptor())
    okBuilder.addNetworkInterceptor(StethoInterceptor())

    return okBuilder.build()
}

private fun loggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun createRetrofit(): AppRetrofit {
    val coreRetrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(makeHttpClient())
        .baseUrl(BASE_URL)
        .build()
    return coreRetrofit.create(AppRetrofit::class.java)
}
