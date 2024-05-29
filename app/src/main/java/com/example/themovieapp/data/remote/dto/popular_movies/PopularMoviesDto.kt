package com.example.themovieapp.data.remote.dto.popular_movies


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class PopularMoviesDto(
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("results")
    @Expose
    val results: List<PopularMoviesResultDto>,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int
)