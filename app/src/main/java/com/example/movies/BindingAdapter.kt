package com.example.movies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movies.movies.adapters.MovieAdapter
import com.example.movies.network.Movie
import com.example.movies.network.MoviesApiStatus
import com.example.movies.network.Person
import com.example.movies.people.adapters.PersonAdapter

private const val BASE_URL_IMG = "https://image.tmdb.org/t/p/w500"

@JvmName("bindRecyclerView1")
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

@JvmName("bindRecyclerView2")
@BindingAdapter("dataPeople")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Person>?) {
    val adapter = recyclerView.adapter as PersonAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    val completeUrl = "${BASE_URL_IMG + imgUrl}"
    imgUrl?.let {
        val imgUri = completeUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("moviesApiStatus")
fun bindStatus(statusImageView: ImageView, status: MoviesApiStatus?) {
    when (status) {
        MoviesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MoviesApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MoviesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("knowFor")
fun knowFor(textView: TextView, data: List<Movie>?) {
    val titleMovies = data?.map { it.title }
    textView.text = "Know for: ${titleMovies?.toString()?.replace("[", "")?.replace("]", "")}"
}
