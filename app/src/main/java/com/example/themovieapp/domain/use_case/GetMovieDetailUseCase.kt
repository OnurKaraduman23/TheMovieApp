package com.example.themovieapp.domain.use_case

import com.example.themovieapp.common.Resource
import com.example.themovieapp.common.extension.tryFlowOrEmitError
import com.example.themovieapp.data.mapper.toUIModel
import com.example.themovieapp.di.IoDispatcher
import com.example.themovieapp.domain.model.ui_model.detail_movie.DetailMovieUIModel
import com.example.themovieapp.domain.model.ui_model.new_movies.NewMovieUIModel
import com.example.themovieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    fun getMovieDetail(movieId: Int): Flow<Resource<DetailMovieUIModel>> =
        tryFlowOrEmitError(dispatcher) {
            val movieDetailDto = moviesRepository.getMovieDetail(movieId)
            val movieDetailUIModel = movieDetailDto.toUIModel()
            movieDetailUIModel
        }

}