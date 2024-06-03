package com.example.themovieapp.domain.model.ui_model.search_movie


data class SearchMoviesUIModel(
    val page: Int,
    val results: List<SearchMovieUIModel>

)