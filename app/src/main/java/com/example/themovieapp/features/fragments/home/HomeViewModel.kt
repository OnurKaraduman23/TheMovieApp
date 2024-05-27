package com.example.themovieapp.features.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovieapp.common.Resource
import com.example.themovieapp.domain.use_case.GetNewMoviesUseCase
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
    private val getNewMoviesUseCase: GetNewMoviesUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<MoviesUIState> =
        MutableStateFlow(MoviesUIState())
    val uiState: StateFlow<MoviesUIState> = _uiState.asStateFlow()

    init {
        getNewMovies()
    }

    private fun getNewMovies() {
        viewModelScope.launch {
            getNewMoviesUseCase.getNewMovies()
                .onStart { _uiState.update { state -> state.copy(isLoading = true) } }
                .onCompletion { _uiState.update { state -> state.copy(isLoading = false) } }
                .collect { dataResult ->
                    when (dataResult) {
                        is Resource.Success -> {
                            _uiState.update { state ->
                                state.copy( newMoviesList =  dataResult?.data!!)

                            }
                        }
                        is Resource.Error -> {}
                        is Resource.Loading -> {}
                    }
                }
        }
    }
}