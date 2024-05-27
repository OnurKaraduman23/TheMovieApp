package com.example.themovieapp.data.service

import com.example.themovieapp.common.Constants
import com.example.themovieapp.data.remote.dto.new_movies.NewMoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieApi {

    @GET("movie/upcoming?language=en-US")
    suspend fun getNewMovies(
        @Query(Constants.QUERY_API_KEY) apiKey: String
    ): NewMoviesDto
}