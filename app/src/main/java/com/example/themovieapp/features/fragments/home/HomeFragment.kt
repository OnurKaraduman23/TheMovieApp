package com.example.themovieapp.features.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.moviecaseapp.common.binding_adapter.BindingFragment
import com.example.themovieapp.databinding.FragmentHomeBinding
import com.example.themovieapp.features.fragments.home.adapter.NewMoviesAdapter
import com.example.themovieapp.features.fragments.home.adapter.PopularMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var newMoviesAdapter: NewMoviesAdapter
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPopularMoviesRecyclerView()
        collectNewMoviesUIState()
        collectPopularMoviesUIState()

    }

    private fun collectPopularMoviesUIState() {
        lifecycleScope.launch {
            homeViewModel.popularMovieList.collectLatest { pagingData ->
                popularMoviesAdapter.submitData(pagingData)
            }
        }
    }

    private fun collectNewMoviesUIState() {
        lifecycleScope.launch {
            homeViewModel.newMoviesUiState.collect { state ->
                if (state.newMoviesList.isNotEmpty()) {
                    newMoviesAdapter = NewMoviesAdapter(state.newMoviesList) { newMovieId ->
                        findNavController().navigate(
                            HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                                newMovieId
                            )
                        )
                    }
                    binding.newMoviesRecyclerView.adapter = newMoviesAdapter
                }
            }
        }
    }


    private fun setupPopularMoviesRecyclerView() {
        popularMoviesAdapter = PopularMoviesAdapter { movieId ->

            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movieId)
            )
        }
        binding.popularMoviesRecyclerView.adapter = popularMoviesAdapter
    }


}