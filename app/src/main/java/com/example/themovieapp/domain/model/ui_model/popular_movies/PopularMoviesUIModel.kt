package com.example.themovieapp.domain.model.ui_model.popular_movies



data class PopularMoviesUIModel(

    val page: Int,
    val results: List<PopularMovieUIModel>,
)