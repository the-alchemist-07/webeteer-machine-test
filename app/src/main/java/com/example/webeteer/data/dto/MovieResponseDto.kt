package com.example.webeteer.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class MovieResponseDto(
    @Json(name = "title")
    val title: String?,
    @Json(name = "homeData")
    val homeData: List<GenreDto>?
) {
    @Keep
    @JsonClass(generateAdapter = true)
    data class GenreDto(
        @Json(name = "id")
        val id: String?,
        @Json(name = "genre")
        val genre: String?,
        @Json(name = "type")
        val type: Int?,
        @Json(name = "movieslist")
        val moviesList: List<MovieDto>?
    ) {
        @Keep
        @JsonClass(generateAdapter = true)
        data class MovieDto(
            @Json(name = "id")
            val id: String?,
            @Json(name = "title")
            val title: String?,
            @Json(name = "desc")
            val desc: String?,
            @Json(name = "genre")
            val genre: List<String>?,
            @Json(name = "release")
            val release: String?,
            @Json(name = "posterurl")
            val posterUrl: String?,
            @Json(name = "rating")
            val rating: String?
        )
    }
}