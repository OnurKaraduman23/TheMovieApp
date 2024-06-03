package com.example.themovieapp.domain.use_case.favorites

import com.example.themovieapp.data.local.favorite_movies.MovieEntity
import com.example.themovieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(): Flow<List<MovieEntity>> {
        return repository.getFavorites()
    }
}