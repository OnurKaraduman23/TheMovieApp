package com.example.themovieapp.domain.use_case.favorites

import com.example.themovieapp.domain.repository.MoviesRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(movieId: Int): Boolean {
        return repository.isFavorite(movieId)
    }
}