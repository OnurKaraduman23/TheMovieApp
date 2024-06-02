package com.example.themovieapp.data.remote.dto.movie_detail


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    @Expose
    val iso31661: String,
    @SerializedName("name")
    @Expose
    val name: String
)