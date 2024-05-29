package com.example.themovieapp.domain.use_case

import androidx.paging.PagingData
import com.example.themovieapp.di.IoDispatcher
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovieUIModel
import com.example.themovieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getPopularMovies(): Flow<PagingData<PopularMovieUIModel>> =
        moviesRepository.getPopularMovies()
            .flowOn(dispatcher)
}