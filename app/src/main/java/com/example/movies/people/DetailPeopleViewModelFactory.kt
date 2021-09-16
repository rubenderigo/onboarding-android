package com.example.movies.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailPeopleViewModelFactory(private val person_id: Int?) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DetailPeopleViewModel(person_id) as T
}