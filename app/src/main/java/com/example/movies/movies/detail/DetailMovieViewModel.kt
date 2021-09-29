package com.example.movies.movies.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.network.DetailMovie
import com.example.movies.network.Movie
import com.example.movies.network.MoviesApi
import com.example.movies.network.MoviesApiStatus
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val movie_id: Int?) : ViewModel() {

    private val _movieCastCrew = MutableLiveData<DetailMovie>()
    val movieCastCrew: LiveData<DetailMovie> = _movieCastCrew

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    init {
        getMovies()
    }

    private fun getMovies() {

        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING
            try {
                _movie.value = MoviesApi.retrofitService.getDetailMovie(movie_id)
                _movieCastCrew.value = MoviesApi.retrofitService.getCreditsMovie(movie_id)
                _status.value = MoviesApiStatus.DONE
            } catch (e: Exception) {
                _movie.value = null
                _movieCastCrew.value = null
                _status.value = MoviesApiStatus.ERROR
            }
        }
    }
}