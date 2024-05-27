package com.example.themovieapp.features.fragments.home

import com.example.themovieapp.domain.model.NewMovieUIModel

data class MoviesUIState(
    val isLoading: Boolean = false,
    val newMoviesList: List<NewMovieUIModel> = emptyList()
)