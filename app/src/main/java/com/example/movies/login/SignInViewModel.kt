package com.example.movies.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.movies.data.User
import com.example.movies.data.UserDao

class SignInViewModel(private val userDao: UserDao): ViewModel() {

    fun retrieveUser(username: String, password: String): LiveData<User> {
        return userDao.getUser(username, password).asLiveData()
    }
}

class SignInViewModelFactory(
    private val userDao: UserDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((SignInViewModel::class.java))) {
            @Suppress("UNCHECKED_CAST")
            return SignInViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}