package com.example.swvlmobilechallenge.apiservices


//
// Created by Abdul Basit on 11/6/2020.
//

/**
 * Status of a resource that is provided to the UI.
 *
 *
 * These are usually created by the Repository classes where they return
 * `LiveData<Resource<T>>` to pass back the latest data to the UI with its fetch status.
 */
enum class Status {
    SUCCESS,
    ERROR,
    INITIAL_LOADING,
    LOADING,
    NO_INTERNET_CONNECTION,
    NO_DATA_FOUND
}






