package com.example.webeteer.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webeteer.app.common.Resource
import com.example.webeteer.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Idle)
    val homeState = _homeState.asStateFlow()

    init {
        getMovieList()
    }

    private fun getMovieList() = viewModelScope.launch {
        movieRepository.getMoviesList().collect { state ->
            when (state) {
                is Resource.Loading -> _homeState.emit(HomeState.Loading)
                is Resource.Success -> _homeState.emit(HomeState.SuccessGenreList(state.value))
                is Resource.Error -> _homeState.emit(HomeState.Error(state.error))
            }
        }
    }
}
