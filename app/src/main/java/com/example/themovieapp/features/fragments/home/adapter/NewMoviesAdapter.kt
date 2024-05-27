package com.example.themovieapp.features.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieapp.common.Constants.IMAGES_BASE_URL
import com.example.themovieapp.common.Constants.IMAGE_200
import com.example.themovieapp.common.extension.loadImageView
import com.example.themovieapp.databinding.NewMoviesCardLayoutBinding
import com.example.themovieapp.domain.model.NewMovieUIModel


class NewMoviesAdapter(
    private val newMoviesList: List<NewMovieUIModel>,
    private val onItemClickListener: (String) -> Unit,
) : RecyclerView.Adapter<NewMoviesAdapter.NewMoviesViewHolder>() {

    class NewMoviesViewHolder(private val binding: NewMoviesCardLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newMovieItem: NewMovieUIModel, onItemClickListener: (String) -> Unit) {
            binding.apply {
                movieTitleTextView.text = newMovieItem.title
                circleImageView.loadImageView(IMAGES_BASE_URL + IMAGE_200 + newMovieItem.posterPath)
            }


//            binding.newMovieItem.setOnClickListener {
//                onItemClickListener.invoke(newMovieItem.id)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMoviesViewHolder {
        val binding = NewMoviesCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewMoviesViewHolder, position: Int) {
        holder.bind(newMoviesList[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return newMoviesList.size
    }
}