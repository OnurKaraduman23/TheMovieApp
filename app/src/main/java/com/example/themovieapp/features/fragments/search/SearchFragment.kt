package com.example.themovieapp.features.fragments.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.themovieapp.databinding.FragmentSearchBinding
import com.example.themovieapp.common.extension.hideKeyboardFrom
import com.example.themovieapp.features.fragments.search.adapter.SearchMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var searchMoviesAdapter: SearchMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)


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
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.isSubmitButtonEnabled = true
        setupSearchMoviesRecyclerView()
        collectPopularMoviesUIState()

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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
