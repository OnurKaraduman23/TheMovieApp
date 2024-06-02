package com.example.themovieapp.data.service

import com.example.themovieapp.common.Constants
import com.example.themovieapp.data.remote.dto.movie_detail.MovieDetailDto
import com.example.themovieapp.data.remote.dto.new_movies.NewMoviesDto
import com.example.themovieapp.data.remote.dto.popular_movies.PopularMoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {

    @GET("movie/upcoming?language=en-US")
    suspend fun getNewMovies(
        @Query(Constants.QUERY_API_KEY) apiKey: String
    ): NewMoviesDto

    @GET("movie/popular?language=en-US")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query(Constants.QUERY_API_KEY) apiKey: String
    ): PopularMoviesDto

    @GET("movie/{movie_id}?language=en-US")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query(Constants.QUERY_API_KEY) apiKey: String
    ): MovieDetailDto
}
