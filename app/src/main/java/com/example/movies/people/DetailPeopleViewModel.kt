package com.example.movies.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.network.*
import kotlinx.coroutines.launch

class DetailPeopleViewModel(private val people_id: Int?) : ViewModel() {

    private val _cast = MutableLiveData<CastPerson>()
    val cast: LiveData<CastPerson> = _cast

    private val _person = MutableLiveData<DetailPerson>()
    val person: LiveData<DetailPerson> = _person

    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    init {
        getMovies()
    }

    private fun getMovies() {

        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING
            try {
                _person.value = MoviesApi.retrofitService.getDetailPerson(people_id)
                _cast.value = MoviesApi.retrofitService.getCastPerson(people_id)
                _status.value = MoviesApiStatus.DONE
            } catch (e: Exception) {
                _person.value = null
                _cast.value = null
                _status.value = MoviesApiStatus.ERROR
            }
        }
    }
}
