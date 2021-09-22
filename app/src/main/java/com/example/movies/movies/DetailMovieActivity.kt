package com.example.movies.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.movies.databinding.ActivityDetailMovieBinding
import com.example.movies.movies.adapters.CastAdapter
import com.example.movies.movies.adapters.MovieAdapter
import com.example.movies.network.CastMovie
import com.example.movies.network.Movie

class DetailMovieActivity : AppCompatActivity(), CastAdapter.OnItemClickListener {

    private val viewModel: DetailMovieViewModel by viewModels {
        DetailMovieViewModelFactory(
            getIdIntent()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.billedCastMovie.adapter = CastAdapter(this)

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

    override fun onItemClick(position: Int, item: CastMovie?) {
        Toast.makeText(this, item?.name.toString(), Toast.LENGTH_SHORT ).show()
    }
}
