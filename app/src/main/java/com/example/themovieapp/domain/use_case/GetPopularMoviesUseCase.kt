package com.example.themovieapp.domain.use_case

import com.example.themovieapp.common.Resource
import com.example.themovieapp.common.extension.tryFlowOrEmitError
import com.example.themovieapp.data.mapper.toUIModel
import com.example.themovieapp.di.IoDispatcher
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovieUIModel
import com.example.themovieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    fun getPopularMovies(page: Int): Flow<Resource<List<PopularMovieUIModel>>> =
        tryFlowOrEmitError(dispatcher){
            val popularMoviesDto = moviesRepository.getPopularMovies(page)
            val popularMovieUIModel = popularMoviesDto.toUIModel()
            popularMovieUIModel.results
        }

}