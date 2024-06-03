package com.example.themovieapp.features.fragments.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import coil.load
import com.example.moviecaseapp.common.binding_adapter.BindingFragment
import com.example.themovieapp.common.Constants
import com.example.themovieapp.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : BindingFragment<FragmentMovieDetailBinding>() {


    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentMovieDetailBinding::inflate

    private val detailMovieViewModel: MovieDetailViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: MovieDetailFragmentArgs by navArgs()
        val movieId = bundle.movieId
        Log.e("Dante", movieId.toString())
        detailMovieViewModel.getMovieDetail(movieId)
        collectMovieDetailUIState()
    }

    private fun collectMovieDetailUIState() {
        lifecycleScope.launch {
            detailMovieViewModel.movieDetailUiState.collectLatest { state ->
                state.detailMovie?.let { detailMovie ->
                    binding.apply {
                        posterImageView.load(Constants.IMAGES_BASE_URL + Constants.IMAGE_500 + detailMovie.posterPath)
                        titleTextView.text = detailMovie.title
                        overviewTextView.text = detailMovie.overview
                        imdbRatingTextView.text = detailMovie.voteAverage.toString()
                        detailsTextView.text = detailMovie.genre
                        releaseDateTextView.text = detailMovie.releaseDate
                    }
                }
            }
        }
    }


}