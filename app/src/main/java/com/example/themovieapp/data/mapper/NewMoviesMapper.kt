package com.example.themovieapp.data.mapper

import com.example.themovieapp.data.remote.dto.new_movies.MovieDto
import com.example.themovieapp.data.remote.dto.new_movies.NewMoviesDto
import com.example.themovieapp.domain.model.ui_model.new_movies.NewMovieUIModel
import com.example.themovieapp.domain.model.ui_model.new_movies.NewMoviesUIModel

fun MovieDto.toUIModel(): NewMovieUIModel {

    return NewMovieUIModel(
        id = this.id!!,
        title = this.title!!,
        overview = this.overview!!,
        voteAverage = this.voteAverage!!,
        posterPath = this.posterPath!!
    )

}
fun NewMoviesDto.toUIModel(): NewMoviesUIModel {
    return NewMoviesUIModel(
        page = this.page,
        results = this.results.map { it.toUIModel() },
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}