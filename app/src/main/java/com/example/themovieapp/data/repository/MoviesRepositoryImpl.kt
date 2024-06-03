package com.example.themovieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.themovieapp.common.Constants
import com.example.themovieapp.data.local.favorite_movies.MovieEntity
import com.example.themovieapp.data.local.favorite_movies.datasource.FavoritesDataSource
import com.example.themovieapp.data.paging.PopularMoviesPagingSource
import com.example.themovieapp.data.paging.SearchMoviesPagingSource
import com.example.themovieapp.data.remote.dto.movie_detail.MovieDetailDto
import com.example.themovieapp.data.remote.dto.new_movies.NewMoviesDto
import com.example.themovieapp.data.service.TheMovieApi
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovieUIModel
import com.example.themovieapp.domain.model.ui_model.search_movie.SearchMovieUIModel
import com.example.themovieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val theMovieApi: TheMovieApi,
    private val favoritesDataSource: FavoritesDataSource
) : MoviesRepository {
    override suspend fun getNewMovies(): NewMoviesDto {
        return theMovieApi.getNewMovies(Constants.API_KEY)
    }

    override suspend fun getPopularMovies(): Flow<PagingData<PopularMovieUIModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PopularMoviesPagingSource(api = theMovieApi)
            }
        ).flow
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetailDto {
        return theMovieApi.getMovieDetail(movieId, Constants.API_KEY)
    }

    override suspend fun getSearchMovies(query: String): Flow<PagingData<SearchMovieUIModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchMoviesPagingSource(api = theMovieApi, query = query)
            }
        ).flow
    }

    override suspend fun addFavorite(
        movieId: Int,
        genreIds: String,
        posterPath: String,
        overview: String,
        releaseDate: String,
        title: String,
        voteAverage: Double
    ) = favoritesDataSource.addFavorites(
        movieId,
        genreIds,
        posterPath,
        overview,
        releaseDate,
        title,
        voteAverage
    )

    override fun getFavorites(): Flow<List<MovieEntity>> =
        favoritesDataSource.getFavorites()


}