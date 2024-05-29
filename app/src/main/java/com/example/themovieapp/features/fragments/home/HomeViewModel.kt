package com.example.themovieapp.features.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovieapp.common.Resource
import com.example.themovieapp.domain.use_case.GetNewMoviesUseCase
import com.example.themovieapp.domain.use_case.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewMoviesUseCase: GetNewMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase

) : ViewModel() {

    private val _newMoviesUiState: MutableStateFlow<NewMoviesUIState> =
        MutableStateFlow(NewMoviesUIState())
    val newMoviesUiState: StateFlow<NewMoviesUIState> = _newMoviesUiState.asStateFlow()

    private val _popularMoviesUiState: MutableStateFlow<PopularMoviesUIState> =
        MutableStateFlow(PopularMoviesUIState())
    val popularMoviesUiState: StateFlow<PopularMoviesUIState> = _popularMoviesUiState.asStateFlow()


    init {
        getNewMovies()
        getPopularMovies(1)
    }

    private fun getNewMovies() {
        viewModelScope.launch {
            getNewMoviesUseCase.getNewMovies()
                .onStart { _newMoviesUiState.update { state -> state.copy(isLoading = true) } }
                .onCompletion { _newMoviesUiState.update { state -> state.copy(isLoading = false) } }
                .collect { dataResult ->
                    when (dataResult) {
                        is Resource.Success -> {
                            _newMoviesUiState.update { state ->
                                state.copy(newMoviesList = dataResult?.data!!)

                            }
                        }

                        is Resource.Error -> {}
                        is Resource.Loading -> {}
                    }
                }
        }
    }


    private fun getPopularMovies(page: Int) {
        viewModelScope.launch {
            getPopularMoviesUseCase.getPopularMovies(page)
                .onStart { _popularMoviesUiState.update { state -> state.copy(isLoading = true) } }
                .onCompletion { _popularMoviesUiState.update { state -> state.copy(isLoading = false) } }
                .collect { dataResult ->
                    when (dataResult) {
                        is Resource.Success -> {
                            _popularMoviesUiState.update { state ->
                                state.copy(popularMoviesList = dataResult.data!!)

                            }
                        }

                        is Resource.Error -> {}
                        is Resource.Loading -> {}
                    }
                }
        }
    }
}