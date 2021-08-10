package com.example.movies.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.movies.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    private val viewModel: DetailMovieViewModel by viewModels { DetailMovieViewModelFactory(getIdIntent()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val view = binding.root
        setContentView(view)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
    }

    private fun getIdIntent(): Int? {
        val intent = getIntent()
        val id = intent.getStringExtra("id")
        return id?.toInt()
    }
}