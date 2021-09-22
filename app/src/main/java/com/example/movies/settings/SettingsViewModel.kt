package com.example.movies.settings

import androidx.lifecycle.*

class SettingsViewModel() : ViewModel() {

    private val _username = MutableLiveData<String>("username")
    val username: LiveData<String> = _username

    private val _email = MutableLiveData<String>("email")
    val email: LiveData<String> = _email

    fun setData(username: String?, email: String?){
        _username.value = username
        _email.value = email
    }

}