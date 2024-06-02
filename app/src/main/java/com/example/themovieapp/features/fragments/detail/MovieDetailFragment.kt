package com.example.themovieapp.features.fragments.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.themovieapp.common.Constants
import com.example.themovieapp.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val detailMovieViewModel: MovieDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        val bundle: MovieDetailFragmentArgs by navArgs()
        val movieId = bundle.movieId
        Log.e("Dante", movieId.toString())
        detailMovieViewModel.getMovieDetail(movieId)
        collectMovieDetailUIState()


        return binding.root
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}