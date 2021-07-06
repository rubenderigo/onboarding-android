package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavegation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.container_bottom_nav)

        bottomNavegation.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.moviesFragment, R.id.peopleFragment, R.id.settingsFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}