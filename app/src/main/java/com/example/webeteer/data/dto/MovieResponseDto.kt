package com.example.webeteer.data.dto

import androidx.annotation.Keep
import com.example.webeteer.domain.model.Genre
import com.example.webeteer.domain.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class MovieResponseDto(
    @Json(name = "title")
    val title: String?,
    @Json(name = "homeData")
    val genreDtoList: List<GenreDto>?
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
        val movieDtoList: List<MovieDto>?
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
        ) {
            fun toMovie(): Movie {
                return Movie(
                    id = id ?: "",
                    title = title ?: "",
                    description = desc ?: "",
                    genreList = genre ?: emptyList(),
                    releaseDate = release ?: "",
                    posterUrl = posterUrl ?: "",
                    rating = rating ?: ""
                )
            }
        }

        fun toGenre(): Genre {
            return Genre(
                id = id ?: "",
                genre = genre ?: "",
                movieList = movieDtoList?.map { it.toMovie() } ?: emptyList()
            )
        }
    }
}
