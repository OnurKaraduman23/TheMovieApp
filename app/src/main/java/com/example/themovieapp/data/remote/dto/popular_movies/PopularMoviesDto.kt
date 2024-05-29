package com.example.themovieapp.data.remote.dto.popular_movies


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class PopularMoviesDto(
    @SerializedName("page")
    @Expose
    val page: Int= 0,
    @SerializedName("results")
    @Expose
    val results: List<PopularMoviesResultDto> = emptyList(),
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int = 0,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int = 0
)