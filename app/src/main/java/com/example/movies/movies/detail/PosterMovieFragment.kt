package com.example.movies.movies.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.example.movies.databinding.FragmentPosterMovieBinding

class PosterMovieFragment : Fragment() {

    private lateinit var binding: FragmentPosterMovieBinding

    private val viewModel: DetailMovieViewModel by viewModels {
        DetailMovieViewModelFactory(
            arguments?.getInt("id")
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPosterMovieBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.data = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as DetailMovieActivity?)?.getSupportActionBar()?.show()
        binding.posterPath.setOnClickListener {
            if (binding.likeDown.visibility == View.GONE) {
                (activity as DetailMovieActivity?)?.getSupportActionBar()?.show()
                binding.likeDown.visibility = View.VISIBLE
                binding.likeUp.visibility = View.VISIBLE
                binding.posterPath.scaleType = ImageView.ScaleType.FIT_START
            } else {
                (activity as DetailMovieActivity?)?.getSupportActionBar()?.hide()
                binding.likeDown.visibility = View.GONE
                binding.likeUp.visibility = View.GONE
                binding.posterPath.scaleType = ImageView.ScaleType.FIT_CENTER
            }
        }
    }
}