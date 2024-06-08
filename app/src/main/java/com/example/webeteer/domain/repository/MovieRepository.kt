package com.example.webeteer.domain.repository

import com.example.webeteer.app.common.Resource
import com.example.webeteer.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviesList(): Flow<Resource<List<Genre>>>

}