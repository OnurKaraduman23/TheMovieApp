package com.example.themovieapp.features.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieapp.common.Constants
import com.example.themovieapp.common.extension.loadImageView
import com.example.themovieapp.databinding.PopularMoviesCardLayoutBinding
import com.example.themovieapp.domain.model.ui_model.popular_movies.PopularMovieUIModel

class PopularMoviesAdapter : PagingDataAdapter<PopularMovieUIModel, PopularMoviesAdapter.PopularMoviesViewHolder>(MovieItemDiffCallback()) {

    var onItemClickListener: ((PopularMovieUIModel) -> Unit)? = null

    inner class PopularMoviesViewHolder(private val binding: PopularMoviesCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: PopularMovieUIModel) {
            binding.apply {
                trendingImageView.loadImageView(Constants.IMAGES_BASE_URL + Constants.IMAGE_400 + result.posterPath)
                root.setOnClickListener {
                    onItemClickListener?.invoke(result)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val binding = PopularMoviesCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }
}

class MovieItemDiffCallback : DiffUtil.ItemCallback<PopularMovieUIModel>() {
    override fun areItemsTheSame(oldItem: PopularMovieUIModel, newItem: PopularMovieUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PopularMovieUIModel, newItem: PopularMovieUIModel): Boolean {
        return oldItem == newItem
    }
}
