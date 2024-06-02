package com.example.themovieapp.data.remote.dto.movie_detail


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class BelongsToCollection(
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String
)