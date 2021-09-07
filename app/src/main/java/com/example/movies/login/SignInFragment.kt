package com.example.movies.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movies.MainActivity
import com.example.movies.MoviesApplication
import com.example.movies.R
import com.example.movies.data.User

class SignInFragment : Fragment() {

    lateinit var navController: NavController

    private val viewModel: SignInViewModel by viewModels {
        SignInViewModelFactory(
            (activity?.application as MoviesApplication).database.userDao()
        )
    }

    private val usernameLiveData = MutableLiveData<String>()
    private val passwordLiveData = MutableLiveData<String>()
    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value = false

        addSource(usernameLiveData) { username ->
            val password = passwordLiveData.value
            this.value = validateForm(username, password)
        }

        addSource(passwordLiveData) { password ->
            val username = usernameLiveData.value
            this.value = validateForm(username, password)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        val sign_up = view.findViewById(R.id.sign_up) as TextView

        sign_up.setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        val usernameLayout = view.findViewById<EditText>(R.id.username_login)
        val passwordLayout = view.findViewById<EditText>(R.id.password_login)
        val signInButton = view.findViewById<Button>(R.id.button_sign_in)

        usernameLayout.doOnTextChanged { text, _, _, _ ->
            usernameLiveData.value = text?.toString()
        }
        passwordLayout.doOnTextChanged { text, _, _, _ ->
            passwordLiveData.value = text?.toString()
        }
        isValidLiveData.observe(viewLifecycleOwner) { isValid ->
            signInButton.isEnabled = isValid
        }

        signInButton.setOnClickListener {
            viewModel.retrieveUser(
                usernameLiveData.value.toString(),
                passwordLiveData.value.toString()
            ).observe(this.viewLifecycleOwner) { selectedUser ->
                if (selectedUser != null) {
                    activity?.let {
                        Toast.makeText(activity, "Sign in succefully", Toast.LENGTH_LONG).show()
                        val intent = Intent(it, MainActivity::class.java)
                        it.startActivity(intent)
                    }
                } else {
                    Toast.makeText(activity, "User not found", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun validateForm(username: String?, password: String?): Boolean {
        val isValidUsername = username != null && username.isNotBlank()
        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        return isValidUsername && isValidPassword
    }
}