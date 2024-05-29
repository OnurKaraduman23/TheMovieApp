package com.example.themovieapp.domain.model.ui_model.new_movies

data class NewMovieUIModel(
    val id: Int,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val posterPath: String
)
