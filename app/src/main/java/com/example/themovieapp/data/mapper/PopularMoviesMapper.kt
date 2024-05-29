package com.example.themovieapp.data.mapper

import com.example.themovieapp.data.remote.dto.popular_movies.PopularMoviesDto
import com.example.themovieapp.data.remote.dto.popular_movies.PopularMoviesResultDto
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovieUIModel
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMoviesUIModel

fun PopularMoviesResultDto.toUIModel(): PopularMovieUIModel {
    return PopularMovieUIModel(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage
    )
}

fun PopularMoviesDto.toUIModel(): PopularMoviesUIModel {
    return PopularMoviesUIModel(
        page = this.page,
        results = this.results.map { it.toUIModel() }
    )
}