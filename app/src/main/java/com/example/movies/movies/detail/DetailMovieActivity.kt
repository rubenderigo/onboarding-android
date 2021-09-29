package com.example.movies.movies.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movies.R

class DetailMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val intent = getIntent()
        val bundle = bundleOf("id" to (intent.getStringExtra("id")?.toInt()))

        val navController = findNavController(R.id.nav_host_detail_movie)
        navController.setGraph(R.navigation.nav_movie_detail, bundle)
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.detailMovieFragment, R.id.infoMovieFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }
}
