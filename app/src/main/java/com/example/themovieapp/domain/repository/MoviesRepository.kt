package com.example.themovieapp.domain.repository


import com.example.themovieapp.data.remote.dto.new_movies.NewMoviesDto




interface MoviesRepository {
    suspend fun getNewMovies(): NewMoviesDto
}