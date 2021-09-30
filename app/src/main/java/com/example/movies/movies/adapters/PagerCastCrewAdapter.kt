package com.example.movies.movies.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movies.movies.detail.castCrew.CastFragment
import com.example.movies.movies.detail.castCrew.CrewFragment

class PagerCastCrewAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                CastFragment()
            }
            1 -> {
                CrewFragment()
            }
            else -> {
                CastFragment()
            }
        }
    }
}