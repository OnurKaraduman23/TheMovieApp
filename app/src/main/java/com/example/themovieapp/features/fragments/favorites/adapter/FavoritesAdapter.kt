package com.example.themovieapp.features.fragments.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieapp.common.Constants
import com.example.themovieapp.common.extension.loadImageView
import com.example.themovieapp.data.local.favorite_movies.MovieEntity
import com.example.themovieapp.databinding.FavoritesCardDesignBinding

class FavoritesAdapter(
    private val onItemClickListenerFavIcon: (MovieEntity) -> Unit,
    private val onItemClickListenerDetail: (Int) -> Unit,

    ) : ListAdapter<MovieEntity, FavoritesAdapter.MyViewHolder>(DiffCallback()) {

    class MyViewHolder(private val binding: FavoritesCardDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            favorites: MovieEntity,
            onItemClickListenerFavIcon: (MovieEntity) -> Unit,
            onItemClickListenerDetail: (Int) -> Unit
        ) {
            binding.apply {
                imageMovie.loadImageView(Constants.IMAGES_BASE_URL + Constants.IMAGE_400 + favorites.posterPath)
                movieTitleFavTextView.text = favorites.title
                overviewFavTextView.text = favorites.overview
                imdbVoteFavtextView.text = favorites.voteAverage.toString()
                addFavImageView.setOnClickListener {
                    onItemClickListenerFavIcon(favorites)
                }
                favCardView.setOnClickListener {
                    onItemClickListenerDetail.invoke(favorites.movieId)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoritesCardDesignBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentFavoriteMovie = getItem(position)
        holder.bind(currentFavoriteMovie, onItemClickListenerFavIcon, onItemClickListenerDetail)
    }

    class DiffCallback : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem == newItem
        }
    }
}
