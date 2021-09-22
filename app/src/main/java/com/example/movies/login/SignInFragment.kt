package com.example.movies.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movies.*
import com.example.movies.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var navController: NavController

    private lateinit var binding: FragmentSignInBinding

    private val viewModel: SignInViewModel by activityViewModels {
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
        val fragmentBinding = FragmentSignInBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding?.data = viewModel

        binding?.signUp?.setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding?.usernameLogin?.doOnTextChanged { text, _, _, _ ->
            usernameLiveData.value = text?.toString()
        }
        binding?.passwordLogin?.doOnTextChanged { text, _, _, _ ->
            passwordLiveData.value = text?.toString()
        }
        isValidLiveData.observe(viewLifecycleOwner) { isValid ->
            binding?.buttonSignIn?.isEnabled = isValid
        }

        binding?.buttonSignIn?.setOnClickListener {
            viewModel.retrieveUser(
                usernameLiveData.value.toString(),
                passwordLiveData.value.toString()
            ).observe(this.viewLifecycleOwner) { user ->
                if (user != null) {
                    activity?.let {
                        Toast.makeText(activity, "Sign in succefully", Toast.LENGTH_LONG).show()
                        val intent = Intent(it, MainActivity::class.java)
                        intent.putExtra("username", user.username)
                        intent.putExtra("email", user.email)
                        it.startActivity(intent)
                        it.finish()
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