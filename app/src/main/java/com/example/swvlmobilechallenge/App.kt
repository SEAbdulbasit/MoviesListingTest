package com.example.swvlmobilechallenge

import android.app.Application

//
// Created by Abdul Basit on 11/4/2020.
//

class App : Application() {

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
}