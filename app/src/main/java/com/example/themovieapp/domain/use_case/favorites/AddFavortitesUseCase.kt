package com.example.themovieapp.domain.use_case.favorites

import com.example.themovieapp.domain.repository.MoviesRepository
import javax.inject.Inject

class AddFavortitesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(
        movieId: Int,
        genreIds: String,
        posterPath: String,
        overview: String,
        releaseDate: String,
        title: String,
        voteAverage: Double
    ) {
        repository.addFavorite(
            movieId,
            genreIds,
            posterPath,
            overview,
            releaseDate,
            title,
            voteAverage
        )
    }
}