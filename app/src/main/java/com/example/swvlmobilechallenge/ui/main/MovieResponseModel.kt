package com.example.swvlmobilechallenge.ui.main

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


//
// Created by Abdul Basit on 11/4/2020.
//

@JsonClass(generateAdapter = true)
@Parcelize
data class MovieResponseModel(
    @Json(name = "movies")
    var movies: List<Movie>
) : Parcelable {
    @JsonClass(generateAdapter = true)
    @Parcelize
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
    ) : Parcelable
}

fun MovieResponseModel.Movie.asDomainModel(): Movie {
    return Movie(
        cast = this.cast?.joinToString(),
        genres = this.genres?.joinToString(),
        rating = this.rating,
        title = this.title,
        year = this.year,

        )
}


@Parcelize
data class Movie(
    var cast: String? = null,
    var genres: String? = null,
    var rating: Int? = null,
    var title: String? = null,
    var year: Int? = null,
) : Parcelable