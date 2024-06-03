package com.example.themovieapp.domain.use_case

import androidx.paging.PagingData
import com.example.themovieapp.di.IoDispatcher
import com.example.themovieapp.domain.model.ui_model.search_movie.SearchMovieUIModel
import com.example.themovieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSearchMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getSearchMovies(query: String): Flow<PagingData<SearchMovieUIModel>> =
        moviesRepository.getSearchMovies(query)
            .flowOn(dispatcher)
}