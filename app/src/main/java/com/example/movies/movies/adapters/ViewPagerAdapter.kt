package com.example.movies.movies.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movies.movies.PopularFragment
import com.example.movies.movies.TopRatedFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                PopularFragment()
            }
            1 -> {
                TopRatedFragment()
            }
            else -> {
                PopularFragment()
            }
        }
    }
}

