package com.example.movies.movies.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movies.R
import com.example.movies.databinding.FragmentDetailMovieBinding
import com.example.movies.movies.adapters.CastAdapter
import com.example.movies.movies.adapters.CastCrewAdapter
import com.example.movies.network.CastMovie

class DetailMovieFragment : Fragment(), CastCrewAdapter.OnItemClickListener {

    private lateinit var navController: NavController

    private lateinit var binding: FragmentDetailMovieBinding

    private val viewModel: DetailMovieViewModel by viewModels {
        DetailMovieViewModelFactory(
            arguments?.getInt("id")
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMovieBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.billedCastMovie.adapter = CastCrewAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as DetailMovieActivity?)?.getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        navController = Navigation.findNavController(view)
        val bundle = bundleOf("id" to arguments?.getInt("id"))

        binding.infoMovie.setOnClickListener {
            navController.navigate(R.id.action_detailMovieFragment_to_infoMovieFragment, bundle)
        }
        binding.posterMovie.setOnClickListener {
            navController.navigate(R.id.action_detailMovieFragment_to_posterMovieFragment, bundle)
        }
        binding.fullCastMovie.setOnClickListener {
            navController.navigate(R.id.action_detailMovieFragment_to_castCrewFragment, bundle)
        }
    }

    override fun onItemClick(position: Int, item: CastMovie?) {
        Toast.makeText(activity, item?.name.toString(), Toast.LENGTH_SHORT).show()
    }
}
