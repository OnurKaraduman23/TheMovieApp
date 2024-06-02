package com.example.themovieapp.data.mapper

import com.example.themovieapp.data.remote.dto.movie_detail.MovieDetailDto
import com.example.themovieapp.domain.model.ui_model.detail_movie.DetailMovieUIModel
import java.util.Locale

fun MovieDetailDto.toUIModel(): DetailMovieUIModel {

    val genreString = this.genres.joinToString(", ") { it.name }

    return DetailMovieUIModel(
        id = this.id,
        title = this.title,
        voteAverage = try {
            String.format(Locale.US, "%,.1f", this.voteAverage).toDouble()
        } catch (e: Exception) {
            0.0
        },
        posterPath = this.posterPath,
        overview = this.overview,
        releaseDate = this.releaseDate,
        genre = genreString
    )
}
