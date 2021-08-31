package com.example.movies.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.example.movies.R
import com.example.movies.signup.SignUpActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private val usernameLiveData = MutableLiveData<String>()
    private val passwordLiveData = MutableLiveData<String>()
    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value=false

        addSource(usernameLiveData){ username ->
            val password = passwordLiveData.value
            this.value = validateForm(username, password)
        }

        addSource(passwordLiveData){ password ->
            val username = usernameLiveData.value
            this.value = validateForm(username, password)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameLayout = findViewById<EditText>(R.id.username_login)
        val passwordLayout = findViewById<EditText>(R.id.password_login)
        val logInButton = findViewById<Button>(R.id.btn_login)

        usernameLayout.doOnTextChanged { text, _, _, _ ->
            usernameLiveData.value = text?.toString()
        }
        passwordLayout.doOnTextChanged { text, _, _, _ ->
            passwordLiveData.value = text?.toString()
        }
        isValidLiveData.observe(this){ isValid ->
            logInButton.isEnabled = isValid
        }

        val sign_up = findViewById(R.id.sign_up) as TextView
        sign_up.setOnClickListener {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
        }
    }

    private fun validateForm(username: String?, password: String?): Boolean{
        val isValidUsername = username != null && username.isNotBlank()
        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        return isValidUsername && isValidPassword
    }
}