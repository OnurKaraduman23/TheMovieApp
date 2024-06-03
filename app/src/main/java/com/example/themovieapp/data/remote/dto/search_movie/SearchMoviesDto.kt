package com.example.themovieapp.data.remote.dto.search_movie


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class SearchMoviesDto(
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("results")
    @Expose
    val results: List<SearchMovieDto>,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int
)