package com.example.themovieapp.features.fragments.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovieapp.data.local.favorite_movies.MovieEntity
import com.example.themovieapp.domain.use_case.favorites.AddFavortitesUseCase
import com.example.themovieapp.domain.use_case.favorites.GetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val addFavoritesUseCase: AddFavortitesUseCase,
    private val getFavoritesUseCase: GetFavoriteUseCase
) : ViewModel() {


    private val _favoritesUiState = MutableStateFlow<List<MovieEntity>>(emptyList())
    val favoritesUiState: StateFlow<List<MovieEntity>> = _favoritesUiState.asStateFlow()
    fun addFavorite(
        movieId: Int,
        genreIds: String,
        posterPath: String,
        overview: String,
        releaseDate: String,
        title: String,
        voteAverage: Double
    ) {
        viewModelScope.launch {
            addFavoritesUseCase(
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

    fun getFavorites() {
        viewModelScope.launch {
            getFavoritesUseCase().collect { favorites ->
                _favoritesUiState.value = favorites
            }
        }
    }
}