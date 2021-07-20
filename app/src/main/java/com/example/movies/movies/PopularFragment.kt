package com.example.movies.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movies.databinding.FragmentPopularBinding
import com.example.movies.movies.adapters.MovieAdapter

class PopularFragment : Fragment() {

    private val viewModel: PopularViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentPopularBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.moviesRv.adapter = MovieAdapter()

        return binding.root
    }
}