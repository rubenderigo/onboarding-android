package com.example.movies.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.network.MoviesApi
import com.example.movies.network.MoviesApiStatus
import com.example.movies.network.Person
import kotlinx.coroutines.launch

class PeopleViewModel : ViewModel() {

    private val _people = MutableLiveData<List<Person>>()
    val people: LiveData<List<Person>> = _people

    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    init {
        getPeople()
    }

    private fun getPeople() {

        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING
            try {
                val listPerson = MoviesApi.retrofitService.getPopularPeople()
                _people.value = listPerson.people
                _status.value = MoviesApiStatus.DONE
            } catch (e: Exception) {
                _people.value = listOf()
                _status.value = MoviesApiStatus.ERROR
            }
        }
    }
}
