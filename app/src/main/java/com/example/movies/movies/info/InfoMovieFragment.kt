package com.example.movies.movies.info

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movies.R
import com.example.movies.databinding.FragmentInfoMovieBinding
import com.example.movies.movies.detail.DetailMovieActivity
import com.example.movies.movies.detail.DetailMovieViewModelFactory
import com.example.movies.movies.detail.DetailMovieViewModel

class InfoMovieFragment : Fragment() {

    private lateinit var navController: NavController

    private lateinit var binding: FragmentInfoMovieBinding

    private val viewModel: DetailMovieViewModel by viewModels {
        DetailMovieViewModelFactory(
            arguments?.getInt("id")
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoMovieBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.data = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        (activity as DetailMovieActivity?)?.getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_settings, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_close_settings -> {
                navController.navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}