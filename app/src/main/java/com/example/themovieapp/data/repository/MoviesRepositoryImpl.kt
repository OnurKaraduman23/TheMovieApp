package com.example.themovieapp.data.repository

import com.example.themovieapp.common.Constants
import com.example.themovieapp.data.remote.dto.new_movies.NewMoviesDto
import com.example.themovieapp.data.service.TheMovieApi
import com.example.themovieapp.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val theMovieApi: TheMovieApi
):MoviesRepository{
    override suspend fun getNewMovies(): NewMoviesDto {
        return theMovieApi.getNewMovies(Constants.API_KEY)
    }
}