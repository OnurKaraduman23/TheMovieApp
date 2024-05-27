package com.example.themovieapp.domain.model

data class NewMoviesUIModel(
    val page: Int,
    val results: List<NewMovieUIModel>,
    val totalPages: Int,
    val totalResults: Int
)
