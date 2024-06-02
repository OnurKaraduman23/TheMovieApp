package com.example.themovieapp.features.fragments.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.themovieapp.R
import com.example.themovieapp.databinding.FragmentHomeBinding
import com.example.themovieapp.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        val bundle:MovieDetailFragmentArgs by navArgs()
        val movieId = bundle.movieId
        Log.e("Dante",movieId.toString())
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}