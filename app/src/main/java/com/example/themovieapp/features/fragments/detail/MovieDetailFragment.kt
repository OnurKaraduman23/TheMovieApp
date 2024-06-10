package com.example.themovieapp.features.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import coil.load
import com.example.moviecaseapp.common.binding_adapter.BindingFragment
import com.example.themovieapp.R
import com.example.themovieapp.common.Constants
import com.example.themovieapp.databinding.FragmentMovieDetailBinding
import com.example.themovieapp.domain.model.ui_model.detail_movie.DetailMovieUIModel
import com.example.themovieapp.features.fragments.favorites.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : BindingFragment<FragmentMovieDetailBinding>() {


    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentMovieDetailBinding::inflate

    private val detailMovieViewModel: MovieDetailViewModel by viewModels()
    private val favoriteViewModel: FavoritesViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: MovieDetailFragmentArgs by navArgs()
        val movieId = bundle.movieId

        detailMovieViewModel.getMovieDetail(movieId)
        collectMovieDetailUIState()
        favoriteViewModel.checkIfFavorite(movieId)

        //check favorites
        observeFavoriteStatus()

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

                        addFavImageView.setOnClickListener {
                            addFavorite(detailMovie)
                        }

                    }
                }
            }
        }
    }

    fun addFavorite(detailMovie: DetailMovieUIModel) {
        favoriteViewModel.addFavorite(
            movieId = detailMovie.id,
            genreIds = detailMovie.genre,
            posterPath = detailMovie.posterPath,
            overview = detailMovie.overview,
            releaseDate = detailMovie.releaseDate,
            title = detailMovie.title,
            voteAverage = detailMovie.voteAverage
        )
    }

    fun setImageViewResource(isFavorite: Boolean) {
        val resource = if (isFavorite) R.drawable.ic_add_fill_fav else R.drawable.ic_add_favorite
        binding.addFavImageView.setImageResource(resource)
    }

    private fun observeFavoriteStatus() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    favoriteViewModel.isFavorite.collect { isFavorite ->
                        setImageViewResource(isFavorite)
                    }
                }
            }
        }
    }


}