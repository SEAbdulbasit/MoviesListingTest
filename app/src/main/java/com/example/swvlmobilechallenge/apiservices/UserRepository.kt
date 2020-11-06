package com.example.swvlmobilechallenge.apiservices

import com.example.swvlmobilechallenge.App
import com.example.swvlmobilechallenge.App.Companion.moshi
import com.example.swvlmobilechallenge.R
import com.example.swvlmobilechallenge.ui.details.FlickrSearchResponseModel
import com.example.swvlmobilechallenge.ui.main.MovieResponseModel
import com.squareup.moshi.JsonAdapter

//
// Created by Abdul Basit on 11/6/2020.
//

class UserRepository {

    //getting the app retrofit instance
    private val retrofit = createRetrofit()
    private val responseHandler = ResponseHandler()

    fun getMoviesList(): MovieResponseModel? {
        val text = App.getInstance().applicationContext.resources.openRawResource(R.raw.movies)
            .bufferedReader().use { it.readText() }

        val jsonAdapter: JsonAdapter<MovieResponseModel> =
            moshi.adapter(MovieResponseModel::class.java)
        return jsonAdapter.fromJson(text)
    }


    suspend fun getMovieImages(movieTittle: String): Resource<FlickrSearchResponseModel> {

        try {
            val apiResults = retrofit.getMovieImages(
                url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=907d729c5f64ce745f9be6de4bdbeb14&format=json&nojsoncallback=1&text=$movieTittle&page=1&per_page=10"
            ).await()

            return if (apiResults.isSuccessful) {
                responseHandler.handleSuccess(
                    apiResults.body(),
                    apiResults.code()
                )
            } else {
                responseHandler.handleException(statusCode = apiResults.code())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return responseHandler.handleException(e)
        }
    }
}