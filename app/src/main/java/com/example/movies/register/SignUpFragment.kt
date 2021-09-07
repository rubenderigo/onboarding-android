package com.example.movies.register

import android.content.Intent
import android.os.Bundle
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
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movies.MainActivity
import com.example.movies.MoviesApplication
import com.example.movies.R

class SignUpFragment : Fragment() {

    lateinit var navController: NavController

    private val viewModel: SignUpViewModel by viewModels {
        SignUpViewModelFactory(
            (activity?.application as MoviesApplication).database.userDao()
        )
    }

    private val emailLiveData = MutableLiveData<String>()
    private val usernameLiveData = MutableLiveData<String>()
    private val passwordLiveData = MutableLiveData<String>()
    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value = false

        addSource(usernameLiveData) { username ->
            val password = passwordLiveData.value
            val email = emailLiveData.value
            this.value = validateForm(email, username, password)
        }

        addSource(emailLiveData) { email ->
            val password = passwordLiveData.value
            val username = usernameLiveData.value
            this.value = validateForm(email, username, password)
        }

        addSource(passwordLiveData) { password ->
            val email = emailLiveData.value
            val username = usernameLiveData.value
            this.value = validateForm(email, username, password)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val sign_in = view.findViewById(R.id.sign_in) as TextView

        sign_in.setOnClickListener {
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        val emailLayout = view.findViewById<EditText>(R.id.email_sign_up)
        val usernameLayout = view.findViewById<EditText>(R.id.username_sign_up)
        val passwordLayout = view.findViewById<EditText>(R.id.password_sign_up)
        val signUpButton = view.findViewById<Button>(R.id.button_sign_up)

        emailLayout.doOnTextChanged { text, _, _, _ ->
            emailLiveData.value = text?.toString()
        }
        usernameLayout.doOnTextChanged { text, _, _, _ ->
            usernameLiveData.value = text?.toString()
        }
        passwordLayout.doOnTextChanged { text, _, _, _ ->
            passwordLiveData.value = text?.toString()
        }
        isValidLiveData.observe(viewLifecycleOwner) { isValid ->
            signUpButton.isEnabled = isValid
        }

        signUpButton.setOnClickListener {
            addNewItem(usernameLiveData.value, emailLiveData.value, passwordLiveData.value)
            Toast.makeText(activity, "Sign Up succefully", Toast.LENGTH_LONG).show()
            activity?.let{
                val intent = Intent (it, MainActivity::class.java)
                it.startActivity(intent)
            }
        }
    }

    private fun addNewItem(username: String?, email: String?, password: String?) {
        if (username != null && email != null && password != null) {
            viewModel.addNewItem(
                username,
                email,
                password
            )
        }
    }

    private fun validateForm(email: String?, username: String?, password: String?): Boolean {
        val isValidUsername = username != null && username.isNotBlank()
        val isValidEmail = email != null && email.isNotBlank() && email.contains("@")
        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        return isValidUsername && isValidPassword && isValidEmail
    }
}