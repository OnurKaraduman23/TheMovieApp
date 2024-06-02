package com.example.themovieapp.features.fragments.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovieapp.common.Resource
import com.example.themovieapp.domain.use_case.GetMovieDetailUseCase
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
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val _movieDetailUiState: MutableStateFlow<DetailMovieUIState> =
        MutableStateFlow(DetailMovieUIState())
    val movieDetailUiState: StateFlow<DetailMovieUIState> = _movieDetailUiState.asStateFlow()


     fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase.getMovieDetail(movieId)
                .onStart { _movieDetailUiState.update { state -> state.copy(isLoading = true) } }
                .onCompletion { _movieDetailUiState.update { state -> state.copy(isLoading = false) } }
                .collect { dataResult ->
                    when (dataResult) {
                        is Resource.Success -> {
                            _movieDetailUiState.update { state ->
                                state.copy(detailMovie = dataResult.data!!)

                            }
                        }

                        is Resource.Error -> {}
                        is Resource.Loading -> {}
                    }
                }
        }
    }
}