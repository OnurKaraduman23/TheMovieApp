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


}