package com.example.themovieapp.domain.repository


import androidx.paging.PagingData
import com.example.themovieapp.data.remote.dto.movie_detail.MovieDetailDto
import com.example.themovieapp.data.remote.dto.new_movies.NewMoviesDto
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovieUIModel
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {
    suspend fun getNewMovies(): NewMoviesDto

    suspend fun getPopularMovies(): Flow<PagingData<PopularMovieUIModel>>
    suspend fun getMovieDetail(movieId: Int): MovieDetailDto
}