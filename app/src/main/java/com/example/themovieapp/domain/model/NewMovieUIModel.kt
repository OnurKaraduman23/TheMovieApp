package com.example.themovieapp.domain.model

data class NewMovieUIModel(
    val id: Int,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val posterPath: String
)
