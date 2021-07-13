package com.example.movies.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.network.Movie
import com.example.movies.network.MoviesApi
import kotlinx.coroutines.launch
import retrofit2.Call

class PopularViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    init {
        getMovies()
        Log.d("PopularViewModel", "result: ${_movies.value}")
    }

    private fun getMovies() {

        viewModelScope.launch {
            try {
                val listMovies = MoviesApi.retrofitService.getPopularMovies()
                _movies.value = listMovies.movies
                Log.d("PopularViewModel", "try: ${_movies.value}")
            } catch (e: Exception) {
                Log.d("PopularViewModel", "exeption: ${e}")
                _movies.value = null
            }
        }
    }
}
