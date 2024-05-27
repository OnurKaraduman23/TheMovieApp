package com.example.themovieapp.data.remote.dto.new_movies


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class NewMoviesDto(
    @SerializedName("dates")
    @Expose
    val dates: Dates,
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("results")
    @Expose
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int
)