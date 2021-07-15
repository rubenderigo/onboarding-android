package com.example.movies.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.network.Movie
import com.example.movies.network.MoviesApi
import kotlinx.coroutines.launch

class PopularViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    init {
        getMovies()
    }

    private fun getMovies() {

        viewModelScope.launch {
            try {
                val listMovies = MoviesApi.retrofitService.getPopularMovies()
                _movies.value = listMovies.movies
            } catch (e: Exception) {
                _movies.value = listOf()
            }
        }
    }
}
