package com.example.themovieapp.features.fragments.detail

import com.example.themovieapp.domain.model.ui_model.detail_movie.DetailMovieUIModel
import com.example.themovieapp.domain.model.ui_model.new_movies.NewMovieUIModel

data class DetailMovieUIState(
    val isLoading: Boolean = false,
    val detailMovie: DetailMovieUIModel? = null
)