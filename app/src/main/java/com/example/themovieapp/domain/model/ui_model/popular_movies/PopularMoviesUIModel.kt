package com.example.themovieapp.domain.model.ui_model.popular_movies

import com.example.themovieapp.data.remote.dto.popular_movies.PopularMoviesResultDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PopularMoviesUIModel(

    val page: Int,
    val results: List<PopularMovieUIModel>,
)