package com.example.themovieapp.features.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.themovieapp.databinding.FragmentHomeBinding
import com.example.themovieapp.features.fragments.home.adapter.NewMoviesAdapter
import com.example.themovieapp.features.fragments.home.adapter.PopularMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var newMoviesAdapter: NewMoviesAdapter
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

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
                popularMoviesAdapter.onItemClickListener = { popularMovieId ->
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                            popularMovieId
                        )
                    )

                }

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
        popularMoviesAdapter = PopularMoviesAdapter()
        binding.popularMoviesRecyclerView.apply {
            adapter = popularMoviesAdapter

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}