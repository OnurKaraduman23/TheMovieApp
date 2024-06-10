package com.example.themovieapp.domain.repository


import androidx.paging.PagingData
import com.example.themovieapp.data.local.favorite_movies.MovieEntity
import com.example.themovieapp.data.remote.dto.movie_detail.MovieDetailDto
import com.example.themovieapp.data.remote.dto.new_movies.NewMoviesDto
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovieUIModel
import com.example.themovieapp.domain.model.ui_model.search_movie.SearchMovieUIModel
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {

    // Remote
    suspend fun getNewMovies(): NewMoviesDto

    suspend fun getPopularMovies(): Flow<PagingData<PopularMovieUIModel>>
    suspend fun getMovieDetail(movieId: Int): MovieDetailDto

    suspend fun getSearchMovies(query: String): Flow<PagingData<SearchMovieUIModel>>

    // Local (for favorites operations)

    suspend fun addFavorite(
        movieId: Int,
        genreIds: String,
        posterPath: String,
        overview: String,
        releaseDate: String,
        title: String,
        voteAverage: Double
    )

    fun getFavorites(): Flow<List<MovieEntity>>

    suspend fun deleteFavorites(movieId: Int)

    suspend fun isFavorite(movieId: Int): Boolean

}