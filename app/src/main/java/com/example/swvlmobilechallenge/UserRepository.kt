package com.example.swvlmobilechallenge

import com.example.swvlmobilechallenge.App.Companion.moshi
import com.example.swvlmobilechallenge.ui.main.MovieResponseModel
import com.squareup.moshi.JsonAdapter

//
// Created by Abdul Basit on 11/4/2020.
//

class UserRepository {

    fun getMoviesList(): MovieResponseModel? {
        val text = App.getInstance().applicationContext.resources.openRawResource(R.raw.movies)
            .bufferedReader().use { it.readText() }

        val jsonAdapter: JsonAdapter<MovieResponseModel> =
            moshi.adapter(MovieResponseModel::class.java)
        return jsonAdapter.fromJson(text)
    }
}