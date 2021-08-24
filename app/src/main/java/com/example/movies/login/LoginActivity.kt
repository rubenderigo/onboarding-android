package com.example.movies.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.movies.R
import com.example.movies.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sign_up = findViewById(R.id.sign_up) as TextView
        sign_up.setOnClickListener {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
        }
    }
}