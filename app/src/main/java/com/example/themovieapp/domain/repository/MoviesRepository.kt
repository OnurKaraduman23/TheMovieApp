package com.example.themovieapp.domain.repository


import com.example.themovieapp.data.remote.dto.new_movies.NewMoviesDto
import com.example.themovieapp.data.remote.dto.popular_movies.PopularMoviesDto


interface MoviesRepository {
    suspend fun getNewMovies(): NewMoviesDto

    suspend fun getPopularMovies(page: Int): PopularMoviesDto
}