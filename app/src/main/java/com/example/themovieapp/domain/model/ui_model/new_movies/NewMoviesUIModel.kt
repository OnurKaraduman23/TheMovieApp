package com.example.themovieapp.domain.model.ui_model.new_movies

data class NewMoviesUIModel(
    val page: Int,
    val results: List<NewMovieUIModel>,
    val totalPages: Int,
    val totalResults: Int
)
