package com.example.movies.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.network.Movie
import com.example.movies.network.MoviesApi
import com.example.movies.network.MoviesApiStatus
import kotlinx.coroutines.launch

class TopRatedViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    init {
        getMovies()
    }

    private fun getMovies() {

        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING
            try {
                val listMovies = MoviesApi.retrofitService.getTopRatedMovies()
                _movies.value = listMovies.movies
                _status.value = MoviesApiStatus.DONE
            } catch (e: Exception) {
                _movies.value = listOf()
                _status.value = MoviesApiStatus.ERROR
            }
        }
    }
}