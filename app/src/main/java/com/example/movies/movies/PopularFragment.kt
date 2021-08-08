package com.example.movies.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.movies.databinding.FragmentPopularBinding
import com.example.movies.movies.adapters.MovieAdapter

class PopularFragment : Fragment(), MovieAdapter.OnItemClickListener {

    private val viewModel: PopularViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentPopularBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.moviesRv.adapter = MovieAdapter(this)

        return binding.root
    }

    override fun onItemClick(position: Int) {
        Log.d("clicked", position.toString())
    }
}
