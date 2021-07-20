package com.example.movies

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movies.movies.adapters.MovieAdapter
import com.example.movies.network.Movie

private const val BASE_URL_IMG = "https://image.tmdb.org/t/p/w500"

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    val completeUrl = "${BASE_URL_IMG+imgUrl}"
    imgUrl?.let {
        val imgUri = completeUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}
