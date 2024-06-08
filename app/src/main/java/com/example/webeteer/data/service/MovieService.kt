package com.example.webeteer.data.service

import com.example.webeteer.data.dto.MovieResponseDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface MovieService {

    @GET("dummy.json")
    suspend fun getMoviesList(): ApiResponse<MovieResponseDto>

}