package com.example.swvlmobilechallenge.ui.mainactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swvlmobilechallenge.baseclasses.BaseViewModel

//
// Created by Abdul Basit on 11/4/2020.
//

class MainActivityViewModel : BaseViewModel() {

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel() as T
            }
            throw IllegalArgumentException("Unable to construct view model")
        }
    }
}