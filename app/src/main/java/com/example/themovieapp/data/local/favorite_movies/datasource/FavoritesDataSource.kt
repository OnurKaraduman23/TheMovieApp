package com.example.themovieapp.data.local.favorite_movies.datasource

import com.example.themovieapp.data.local.favorite_movies.MovieDao
import com.example.themovieapp.data.local.favorite_movies.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    suspend fun addFavorites(
        movieId: Int,
        genreIds: String,
        posterPath: String,
        overview: String,
        releaseDate: String,
        title: String,
        voteAverage: Double
    ) {
        val newFav = MovieEntity(
            0,
            movieId = movieId,
            genreIds = genreIds,
            posterPath = posterPath,
            overview = overview,
            releaseDate = releaseDate,
            title = title,
            voteAverage = voteAverage
        )
        movieDao.addFavorite(newFav)
    }

    fun getFavorites(): Flow<List<MovieEntity>> =
        movieDao.getFavorites()

    suspend fun deleteFavorites(movieId: Int) =
        movieDao.deleteFav(movieId)

    suspend fun isFavorite(movieId: Int): Boolean {
        return movieDao.isFavorite(movieId)
    }
}