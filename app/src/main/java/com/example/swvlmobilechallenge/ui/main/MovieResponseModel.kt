package com.example.swvlmobilechallenge.ui.main

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


//
// Created by Abdul Basit on 11/4/2020.
//

@JsonClass(generateAdapter = true)
data class MovieResponseModel(
    @Json(name = "movies")
    var movies: List<Movie>
) {
    @JsonClass(generateAdapter = true)
    data class Movie(
        @Json(name = "cast")
        var cast: List<String?>? = null,
        @Json(name = "genres")
        var genres: List<String?>? = null,
        @Json(name = "rating")
        var rating: Int? = null,
        @Json(name = "title")
        var title: String? = null,
        @Json(name = "year")
        var year: Int? = null,
        var isHeader: Boolean = false
    )
}