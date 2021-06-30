package com.example.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.network.Movie
import com.example.movies.network.MoviesApi
import kotlinx.coroutines.launch

class PopularViewModel: ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    init {
        getPopularMovies()
        Log.d("PopularViewModel", "${_movies.value?.size}")
    }

     private fun getPopularMovies() {
        viewModelScope.launch {
            try {
                _movies.value = MoviesApi.retrofitService.getPopularMovies()
            } catch (e: Exception) {
                _movies.value = listOf()
            }
        }
    }
}
