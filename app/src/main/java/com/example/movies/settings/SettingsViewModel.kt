package com.example.movies.settings

import android.util.Log
import androidx.lifecycle.*
import com.example.movies.data.User
import com.example.movies.data.UserDao
import kotlinx.coroutines.launch

class DataViewModel(private val userDao: UserDao) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    init {
        Log.d("settings", "${_username.value}")
    }

    fun setDataUser(username: String, email: String) {
        _username.value = username
        _email.value = email
    }

    fun retrieveUser(username: String, password: String): LiveData<User> {
        return userDao.getUser(username, password).asLiveData()
    }

    private fun insertUser(user: User) {
        viewModelScope.launch {
            userDao.insert(user)
        }
    }

    private fun getNewItemEntry(username: String, email: String, password: String): User {
        return User(
            username = username,
            email = email,
            password = password
        )
    }

    fun addNewItem(username: String, email: String, password: String) {
        val newUser = getNewItemEntry(username, email, password)
        insertUser(newUser)
    }
}

class DataViewModelFactory(
    private val userDao: UserDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((DataViewModel::class.java))) {
            @Suppress("UNCHECKED_CAST")
            return DataViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}