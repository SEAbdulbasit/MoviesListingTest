package com.example.swvlmobilechallenge.ui.details

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


//
// Created by Abdul Basit on 11/6/2020.
//

@JsonClass(generateAdapter = true)
data class FlickrSearchResponseModel(
    @Json(name = "photos")
    var photos: Photos?,
    @Json(name = "stat")
    var stat: String?
) {
    @JsonClass(generateAdapter = true)
    data class Photos(
        @Json(name = "page")
        var page: Int?,
        @Json(name = "pages")
        var pages: Int?,
        @Json(name = "perpage")
        var perpage: Int?,
        @Json(name = "photo")
        var photo: List<Photo>?,
        @Json(name = "total")
        var total: String?
    ) {
        @JsonClass(generateAdapter = true)
        data class Photo(
            @Json(name = "farm")
            var farm: Int?,
            @Json(name = "id")
            var id: String?,
            @Json(name = "isfamily")
            var isfamily: Int?,
            @Json(name = "isfriend")
            var isfriend: Int?,
            @Json(name = "ispublic")
            var ispublic: Int?,
            @Json(name = "owner")
            var owner: String?,
            @Json(name = "secret")
            var secret: String?,
            @Json(name = "server")
            var server: String?,
            @Json(name = "title")
            var title: String?
        )
    }
}