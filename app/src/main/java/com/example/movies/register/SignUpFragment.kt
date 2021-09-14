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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movies.MainActivity
import com.example.movies.MoviesApplication
import com.example.movies.R
import com.example.movies.databinding.FragmentSettingsBinding
import com.example.movies.databinding.FragmentSignUpBinding
import com.example.movies.settings.DataViewModel
import com.example.movies.settings.DataViewModelFactory

class SignUpFragment : Fragment() {

    private lateinit var navController: NavController

    private lateinit var binding: FragmentSignUpBinding

    private val viewModel: DataViewModel by activityViewModels {
        DataViewModelFactory(
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
        val fragmentBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.data = viewModel
        navController = Navigation.findNavController(view)

        binding?.signIn.setOnClickListener {
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding?.emailSignUp.doOnTextChanged { text, _, _, _ ->
            emailLiveData.value = text?.toString()
        }
        binding?.usernameSignUp.doOnTextChanged { text, _, _, _ ->
            usernameLiveData.value = text?.toString()
        }
        binding?.passwordSignUp.doOnTextChanged { text, _, _, _ ->
            passwordLiveData.value = text?.toString()
        }
        isValidLiveData.observe(viewLifecycleOwner) { isValid ->
            binding?.buttonSignUp.isEnabled = isValid
        }

        binding?.buttonSignUp.setOnClickListener {
            addNewItem(usernameLiveData.value, emailLiveData.value, passwordLiveData.value)
            Toast.makeText(activity, "Sign Up succefully", Toast.LENGTH_LONG).show()
            activity?.let{
                val intent = Intent (it, MainActivity::class.java)
                it.startActivity(intent)
                it.finish()
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