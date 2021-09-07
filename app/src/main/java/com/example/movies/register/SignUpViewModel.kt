package com.example.movies.register

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movies.data.User
import com.example.movies.data.UserDao
import kotlinx.coroutines.launch

class SignUpViewModel(private val userDao: UserDao) : ViewModel() {

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

class SignUpViewModelFactory(
    private val userDao: UserDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((SignUpViewModel::class.java))) {
            @Suppress("UNCHECKED_CAST")
            return SignUpViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
