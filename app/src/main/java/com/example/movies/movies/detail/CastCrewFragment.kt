package com.example.movies.movies.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movies.databinding.FragmentCastCrewBinding
import com.example.movies.movies.adapters.PagerCastCrewAdapter
import com.google.android.material.tabs.TabLayoutMediator

class CastCrewFragment : Fragment() {

    private lateinit var binding: FragmentCastCrewBinding

    private val viewModel: DetailMovieViewModel by viewModels {
        DetailMovieViewModelFactory(
            arguments?.getInt("id")
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCastCrewBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.data = viewModel
        //binding.billedCastMovie.adapter = CastAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pagerCastMovie.adapter = PagerCastCrewAdapter(this)

        val tabLayoutMediator = TabLayoutMediator(binding.tabCastCrew, binding.pagerCastMovie,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "Cast"
                    }
                    1 -> {
                        tab.text = "Crew"
                    }
                }
            })
        tabLayoutMediator.attach()
    }
}