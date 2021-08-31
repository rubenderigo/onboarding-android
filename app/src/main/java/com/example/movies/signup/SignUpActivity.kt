package com.example.movies.signup

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
import com.example.movies.login.LoginActivity

class SignUpActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val emailLayout = findViewById<EditText>(R.id.email_sign_up)
        val usernameLayout = findViewById<EditText>(R.id.username_sign_up)
        val passwordLayout = findViewById<EditText>(R.id.password_sign_up)
        val signUpButton = findViewById<Button>(R.id.btn_sign_up)

        emailLayout.doOnTextChanged { text, _, _, _ ->
            emailLiveData.value = text?.toString()
        }
        usernameLayout.doOnTextChanged { text, _, _, _ ->
            usernameLiveData.value = text?.toString()
        }
        passwordLayout.doOnTextChanged { text, _, _, _ ->
            passwordLiveData.value = text?.toString()
        }
        isValidLiveData.observe(this) { isValid ->
            signUpButton.isEnabled = isValid
        }

        val login = findViewById(R.id.login) as TextView
        login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateForm(email: String?, username: String?, password: String?): Boolean {
        val isValidUsername = username != null && username.isNotBlank()
        val isValidEmail = email != null && email.isNotBlank() && email.contains("@")
        val isValidPassword = password != null && password.isNotBlank() && password.length >= 6
        return isValidUsername && isValidPassword && isValidEmail
    }
}