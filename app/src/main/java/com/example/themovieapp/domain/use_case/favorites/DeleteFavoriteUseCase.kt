package com.example.themovieapp.domain.use_case.favorites

import com.example.themovieapp.domain.repository.MoviesRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(movieId: Int) {
        return repository.deleteFavorites(movieId)
    }
}