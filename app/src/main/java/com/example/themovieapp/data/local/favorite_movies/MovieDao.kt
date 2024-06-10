package com.example.themovieapp.data.local.favorite_movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {

    @Insert
    suspend fun addFavorite(favMovie: MovieEntity)

    @Query("SELECT * FROM favorites")
    fun getFavorites(): Flow<List<MovieEntity>>

    @Query("DELETE FROM favorites WHERE movieId = :movieId")
    suspend fun deleteFav(movieId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE movieId = :movieId)")
    suspend fun isFavorite(movieId: Int): Boolean

}