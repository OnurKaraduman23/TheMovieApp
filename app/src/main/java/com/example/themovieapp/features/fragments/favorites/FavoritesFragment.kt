package com.example.themovieapp.features.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.moviecaseapp.common.binding_adapter.BindingFragment
import com.example.themovieapp.databinding.FragmentFavoritesBinding
import com.example.themovieapp.features.fragments.favorites.adapter.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : BindingFragment<FragmentFavoritesBinding>() {

    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private lateinit var favoritesAdapter: FavoritesAdapter

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentFavoritesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        favoritesViewModel.getFavorites()
        collectFavoritesUIState()

    }

    private fun collectFavoritesUIState() {
        lifecycleScope.launch {
            favoritesViewModel.favoritesUiState.collectLatest { favorites ->
                favoritesAdapter.submitList(favorites)
            }
        }
    }

    private fun setupRecyclerView() {
        favoritesAdapter = FavoritesAdapter({ movieEntity ->
        }, { movieId ->
            findNavController().navigate(
                FavoritesFragmentDirections.actionFavoritesFragmentToMovieDetailFragment(movieId)
            )
        }, { movieId ->
            lifecycleScope.launch {
                favoritesViewModel.deleteFavorites(movieId)
            }
        })
        binding.favoriteRecyvlerView.apply {
            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }


}