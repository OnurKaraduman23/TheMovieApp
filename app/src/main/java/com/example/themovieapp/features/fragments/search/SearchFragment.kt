package com.example.themovieapp.features.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.moviecaseapp.common.binding_adapter.BindingFragment
import com.example.themovieapp.databinding.FragmentSearchBinding
import com.example.themovieapp.common.extension.hideKeyboardFrom
import com.example.themovieapp.features.fragments.search.adapter.SearchMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : BindingFragment<FragmentSearchBinding>() {
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var searchMoviesAdapter: SearchMoviesAdapter
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSearchBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.isSubmitButtonEnabled = true
        setupSearchMoviesRecyclerView()
        collectPopularMoviesUIState()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    view?.let { hideKeyboardFrom(requireContext(), it) }
                    searchViewModel.getSearchMovies(query)
                    collectPopularMoviesUIState()
                    setupSearchMoviesRecyclerView()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

    }


    private fun collectPopularMoviesUIState() {
        lifecycleScope.launch {
            searchViewModel.searchMovieList.collectLatest { pagingData ->
                searchMoviesAdapter.submitData(pagingData)
                Log.e("Dante", "i am searchMovieList")
            }
        }
    }

    private fun setupSearchMoviesRecyclerView() {
        searchMoviesAdapter = SearchMoviesAdapter { movieId ->

            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToMovieDetailFragment(movieId)
            )
        }
        binding.saerchRecyclerView.adapter = searchMoviesAdapter
    }


}
