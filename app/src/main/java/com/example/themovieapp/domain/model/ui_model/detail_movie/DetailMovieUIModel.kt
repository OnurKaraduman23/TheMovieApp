package com.example.themovieapp.domain.model.ui_model.detail_movie

data class DetailMovieUIModel(
    val id: Int,
    val title: String,
    val voteAverage: Double,
    val posterPath: String,
    val overview: String,
    val releaseDate: String,
    val genre: String
)