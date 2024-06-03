package com.example.themovieapp.data.local.favorite_movies

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieDatabase() : RoomDatabase() {

    abstract fun getFavorites(): MovieDao
}