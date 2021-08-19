package com.example.movies.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailMovieViewModelFactory(private val movie_id: Int?): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = DetailMovieViewModel(movie_id) as T
}