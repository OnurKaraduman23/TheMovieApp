package com.example.themovieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.themovieapp.common.Constants
import com.example.themovieapp.data.paging.PopularMoviesPagingSource
import com.example.themovieapp.data.remote.dto.new_movies.NewMoviesDto
import com.example.themovieapp.data.service.TheMovieApi
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovieUIModel
import com.example.themovieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val theMovieApi: TheMovieApi
) : MoviesRepository {
    override suspend fun getNewMovies(): NewMoviesDto {
        return theMovieApi.getNewMovies(Constants.API_KEY)
    }

    override suspend fun getPopularMovies(): Flow<PagingData<PopularMovieUIModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PopularMoviesPagingSource(api = theMovieApi)
            }
        ).flow
    }
}