package com.example.themovieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.themovieapp.common.Constants
import com.example.themovieapp.data.mapper.toUIModel
import com.example.themovieapp.data.service.TheMovieApi
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovieUIModel
import com.example.themovieapp.domain.model.ui_model.search_movie.SearchMovieUIModel
import javax.inject.Inject

class SearchMoviesPagingSource @Inject constructor(
    private val api: TheMovieApi,
    private val query: String
) : PagingSource<Int, SearchMovieUIModel>() {
    override fun getRefreshKey(state: PagingState<Int, SearchMovieUIModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchMovieUIModel> {
        val currentPage = params.key ?: Constants.STARTING_PAGE_INDEX
        return try {
            val response =
                api.getSearchMovies(page = currentPage, query = query, apiKey = Constants.API_KEY)
            val searchMovieList = response.toUIModel()
            LoadResult.Page(
                data = searchMovieList.results,
                prevKey = if (currentPage == Constants.STARTING_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (searchMovieList.results.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
