package com.example.swvlmobilechallenge

import android.app.Application
import com.example.swvlmobilechallenge.apiservices.MoviesRepository
import com.example.swvlmobilechallenge.apiservices.createRetrofit
import com.facebook.stetho.Stetho
import timber.log.Timber

//
// Created by Abdul Basit on 11/4/2020.
//

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(this)

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
    }

    private var moviesRepository: MoviesRepository? = null
    fun getUserRepository(): MoviesRepository {
        synchronized(App::class.java) {
            if (moviesRepository == null)
                moviesRepository = MoviesRepository(createRetrofit())
        }
        return moviesRepository!!
    }
}