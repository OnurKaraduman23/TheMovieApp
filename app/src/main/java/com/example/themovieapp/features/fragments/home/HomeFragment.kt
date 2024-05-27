package com.example.themovieapp.features.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.themovieapp.R
import com.example.themovieapp.databinding.FragmentHomeBinding
import com.example.themovieapp.features.fragments.home.adapter.NewMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var newMoviesAdapter: NewMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectUIState()
    }


    private fun collectUIState() {
        lifecycleScope.launch {
            homeViewModel.uiState.collect { state ->
                if (state.newMoviesList.isNotEmpty()) {
                    newMoviesAdapter = NewMoviesAdapter(state.newMoviesList) { newMovies ->
//                        findNavController().navigate(
//                            HomeFragmentDirections.actionHomeFragmentTo....(
//
//                            )
//                        )
                    }
                    binding.newMoviesRecyclerView.adapter = newMoviesAdapter
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}