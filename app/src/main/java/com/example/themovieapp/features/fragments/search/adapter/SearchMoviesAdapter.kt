package com.example.themovieapp.features.fragments.search.adapter

import android.media.MediaPlayer.OnInfoListener
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.themovieapp.common.Constants
import com.example.themovieapp.databinding.SearchItemCardBinding
import com.example.themovieapp.domain.model.ui_model.search_movie.SearchMovieUIModel

class SearchMoviesAdapter(
    private val onItemClickListener: (Int) -> Unit
) : PagingDataAdapter<SearchMovieUIModel, SearchMoviesAdapter.SearchMoviesViewHolder>(
    MovieItemDiffCallback()
) {


    inner class SearchMoviesViewHolder(private val binding: SearchItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: SearchMovieUIModel, onItemClickListener: (Int) -> Unit) {
            binding.apply {

                searchMovieImage.load(Constants.IMAGES_BASE_URL + Constants.IMAGE_400 + result.posterPath)
                searchMovieTitle.text = result.title
                releaseDateTextView.text = result.releaseDate
                imdbVoteTextView.text = result.voteAverage
                movieCard.setOnClickListener {
                    onItemClickListener.invoke(result.id)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMoviesViewHolder {
        val binding =
            SearchItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchMoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie, onItemClickListener)
        }
    }
}

class MovieItemDiffCallback : DiffUtil.ItemCallback<SearchMovieUIModel>() {
    override fun areItemsTheSame(
        oldItem: SearchMovieUIModel,
        newItem: SearchMovieUIModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: SearchMovieUIModel,
        newItem: SearchMovieUIModel
    ): Boolean {
        return oldItem == newItem
    }
}
