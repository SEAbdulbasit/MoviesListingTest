package com.example.swvlmobilechallenge.apiservices

import com.example.swvlmobilechallenge.ui.details.FlickrSearchResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

//
// Created by Abdul Basit on 11/6/2020.
//

interface AppRetrofit {

    @GET
    fun getMovieImagesAsync(
        @Url url: String
    ): Deferred<Response<FlickrSearchResponseModel>>

}