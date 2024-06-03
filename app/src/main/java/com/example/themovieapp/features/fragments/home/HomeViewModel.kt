package com.example.themovieapp.features.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.themovieapp.common.Resource
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovieUIModel
import com.example.themovieapp.domain.use_case.GetNewMoviesUseCase
import com.example.themovieapp.domain.use_case.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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

    private val _popularMovieList = MutableStateFlow<PagingData<PopularMovieUIModel>>(PagingData.empty())
    val popularMovieList: StateFlow<PagingData<PopularMovieUIModel>> get()= _popularMovieList

    init {
        getNewMovies()
        getPopularMovies()
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
                                state.copy(newMoviesList = dataResult.data!!)

                            }
                        }

                        is Resource.Error -> {}
                        is Resource.Loading -> {}
                    }
                }
        }
    }

   private fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase.getPopularMovies()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _popularMovieList.value = pagingData
                }
        }
    }



}