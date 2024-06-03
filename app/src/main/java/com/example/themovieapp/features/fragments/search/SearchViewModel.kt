package com.example.themovieapp.features.fragments.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovieUIModel
import com.example.themovieapp.domain.model.ui_model.search_movie.SearchMovieUIModel
import com.example.themovieapp.domain.use_case.GetNewMoviesUseCase
import com.example.themovieapp.domain.use_case.GetSearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchMoviesUseCase: GetSearchMoviesUseCase,
) : ViewModel() {


    private val _searchMovieList =
        MutableStateFlow<PagingData<SearchMovieUIModel>>(PagingData.empty())
    val searchMovieList: StateFlow<PagingData<SearchMovieUIModel>> get() = _searchMovieList

    fun getSearchMovies(query: String) {
        viewModelScope.launch {
            getSearchMoviesUseCase.getSearchMovies(query)
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _searchMovieList.value = pagingData
                }
        }
    }

}