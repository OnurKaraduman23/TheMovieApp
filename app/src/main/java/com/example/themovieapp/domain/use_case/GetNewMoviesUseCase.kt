package com.example.themovieapp.domain.use_case

import com.example.themovieapp.common.Resource
import com.example.themovieapp.common.extension.tryFlowOrEmitError
import com.example.themovieapp.data.mapper.toUIModel
import com.example.themovieapp.di.IoDispatcher
import com.example.themovieapp.domain.model.ui_model.new_movies.NewMovieUIModel
import com.example.themovieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetNewMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    fun getNewMovies(): Flow<Resource<List<NewMovieUIModel>>> =
        tryFlowOrEmitError(dispatcher) {
            // NewMoviesDto -> NewMoviesUIModel dönüşümünü kullanarak
            val newMoviesDto = moviesRepository.getNewMovies()
            val newMoviesUIModel = newMoviesDto.toUIModel()
            newMoviesUIModel.results  // Bu satır, NewMoviesUIModel içindeki NewMovieUIModel listesini döndürür
        }
}