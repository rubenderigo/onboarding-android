package com.example.movies.movies.detail.castCrew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movies.databinding.FragmentCastBinding
import com.example.movies.movies.adapters.CastAdapter
import com.example.movies.movies.detail.DetailMovieViewModel
import com.example.movies.movies.detail.DetailMovieViewModelFactory

class CastFragment(id: Int?) : Fragment() {
    private lateinit var binding: FragmentCastBinding

    private val viewModel: DetailMovieViewModel by viewModels {
        DetailMovieViewModelFactory(
            id
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCastBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.data = viewModel
        binding.castRv.adapter = CastAdapter()
        return binding.root
    }
}
