package com.example.webeteer.data.repository

import com.example.webeteer.app.common.Constants.GENERIC_ERROR_MESSAGE
import com.example.webeteer.app.common.Constants.NO_INTERNET_ERROR_MESSAGE
import com.example.webeteer.app.common.Constants.SERVER_ERROR
import com.example.webeteer.app.common.Resource
import com.example.webeteer.data.dto.MovieResponseDto
import com.example.webeteer.data.service.MovieService
import com.example.webeteer.domain.model.Genre
import com.example.webeteer.domain.repository.MovieRepository
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepository {

    override fun getMoviesList(): Flow<Resource<List<Genre>>> = flow {
        emit(Resource.Loading)

        movieService.getMoviesList()
            .suspendOnSuccess {
                val movieResponseDto: MovieResponseDto = this.data
                if (!movieResponseDto.genreDtoList.isNullOrEmpty()) {
                    val genreList = movieResponseDto.genreDtoList.map { it.toGenre() }
                    emit(Resource.Success(genreList))
                } else emit(Resource.Error(EMPTY_GENRES))
            }
            .suspendOnError {
                when (this.statusCode) {
                    StatusCode.InternalServerError -> emit(Resource.Error(SERVER_ERROR))
                    else -> emit(Resource.Error(GENERIC_ERROR_MESSAGE))
                }
            }
            .suspendOnFailure {
                emit(Resource.Error(NO_INTERNET_ERROR_MESSAGE))
            }
    }

    companion object {
        const val EMPTY_GENRES = "No genres found!"
    }
}