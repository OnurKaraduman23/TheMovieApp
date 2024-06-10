package com.example.themovieapp.features.fragments.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovieapp.data.local.favorite_movies.MovieEntity
import com.example.themovieapp.domain.use_case.favorites.AddFavortitesUseCase
import com.example.themovieapp.domain.use_case.favorites.DeleteFavoriteUseCase
import com.example.themovieapp.domain.use_case.favorites.GetFavoriteUseCase
import com.example.themovieapp.domain.use_case.favorites.IsFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val addFavoritesUseCase: AddFavortitesUseCase,
    private val getFavoritesUseCase: GetFavoriteUseCase,
    private var deleteFavoritesUseCase: DeleteFavoriteUseCase,
    private var isFavoriteUseCase: IsFavoriteUseCase
) : ViewModel() {


    private val _favoritesUiState = MutableStateFlow<List<MovieEntity>>(emptyList())
    val favoritesUiState: StateFlow<List<MovieEntity>> = _favoritesUiState.asStateFlow()


    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

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
            if (!isFavoriteUseCase(movieId)) {
                addFavoritesUseCase(
                    movieId,
                    genreIds,
                    posterPath,
                    overview,
                    releaseDate,
                    title,
                    voteAverage
                )
                Log.e("Dante", "Eklendi isFavorite: ${_isFavorite.value}")
                _isFavorite.value = true
                Log.e("Dante", "Eklendi isFavorite: ${_isFavorite.value}")
            } else {
                deleteFavorites(movieId)
                Log.e("Dante", "Silindi isFavorite: ${_isFavorite.value}")
                _isFavorite.value = false
                Log.e("Dante", "Silindi isFavorite: ${_isFavorite.value}")
            }
            checkIfFavorite(movieId)

        }
    }

    fun getFavorites() {
        viewModelScope.launch {
            getFavoritesUseCase().collect { favorites ->
                _favoritesUiState.value = favorites
            }
        }
    }

    fun deleteFavorites(movieId: Int) {
        viewModelScope.launch {
            deleteFavoritesUseCase(movieId)
        }
    }

    fun checkIfFavorite(
        movieId: Int
    ) {
        viewModelScope.launch {
            _isFavorite.value = isFavoriteUseCase(movieId)
        }
    }
}