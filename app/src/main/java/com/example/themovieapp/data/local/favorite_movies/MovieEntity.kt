package com.example.themovieapp.data.local.favorite_movies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val favId: Int,
    val movieId: Int,
    val genreIds: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
)