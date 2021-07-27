package com.example.movies.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.movies.R
import com.example.movies.databinding.FragmentPopularBinding
import com.example.movies.databinding.FragmentTopRatedBinding
import com.example.movies.movies.adapters.MovieAdapter

class TopRatedFragment : Fragment() {

    private val viewModel: TopRatedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentTopRatedBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.moviesRv.adapter = MovieAdapter()

        return binding.root
    }
}