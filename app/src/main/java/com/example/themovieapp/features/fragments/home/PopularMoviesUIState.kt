package com.example.themovieapp.features.fragments.home

import com.example.themovieapp.domain.model.ui_model.new_movies.NewMovieUIModel
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovieUIModel

data class PopularMoviesUIState(
    val isLoading: Boolean = false,
    val popularMoviesList: List<PopularMovieUIModel> = emptyList()
)