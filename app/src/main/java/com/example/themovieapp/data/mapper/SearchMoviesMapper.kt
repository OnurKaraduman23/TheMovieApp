package com.example.themovieapp.data.mapper


import com.example.themovieapp.data.remote.dto.popular_movies.PopularMoviesDto
import com.example.themovieapp.data.remote.dto.popular_movies.PopularMoviesResultDto
import com.example.themovieapp.data.remote.dto.search_movie.SearchMovieDto
import com.example.themovieapp.data.remote.dto.search_movie.SearchMoviesDto
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovieUIModel
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMoviesUIModel
import com.example.themovieapp.domain.model.ui_model.search_movie.SearchMovieUIModel
import com.example.themovieapp.domain.model.ui_model.search_movie.SearchMoviesUIModel
import java.util.Locale

fun SearchMovieDto.toUIModel(): SearchMovieUIModel {

    return SearchMovieUIModel(
        id = this.id,
        title = this.title,
        posterPath = this.posterPath,
        voteAverage = try {
            String.format(Locale.US, "%,.1f", this.voteAverage).toString()
        } catch (e: Exception) {
            "Unknown"
        },
        releaseDate = try {
            this.releaseDate.substring(0, 4)
        } catch (e: Exception) {
            "Unknown"
        }

    )

}

fun SearchMoviesDto.toUIModel(): SearchMoviesUIModel {
    return SearchMoviesUIModel(
        page = this.page,
        results = this.results.map { it.toUIModel() }
    )
}