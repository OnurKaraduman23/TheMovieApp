package com.example.themovieapp.features.fragments.home

import com.example.themovieapp.domain.model.ui_model.new_movies.NewMovieUIModel

data class NewMoviesUIState(
    val isLoading: Boolean = false,
    val newMoviesList: List<NewMovieUIModel> = emptyList()
)