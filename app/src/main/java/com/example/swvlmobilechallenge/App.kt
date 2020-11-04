package com.example.swvlmobilechallenge

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import timber.log.Timber

//
// Created by Abdul Basit on 11/4/2020.
//

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null
        fun getInstance(): App {
            synchronized(App::class.java) {
                if (instance == null)
                    instance =
                        App()
            }
            return instance!!
        }

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    }


    private var userRepository: UserRepository? = null
    fun getUserRepository(): UserRepository {
        synchronized(App::class.java) {
            if (userRepository == null)
                userRepository = UserRepository()
        }
        return userRepository!!
    }


}