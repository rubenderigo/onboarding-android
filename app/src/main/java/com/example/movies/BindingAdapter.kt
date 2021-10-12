package com.example.movies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movies.movies.adapters.CastAdapter
import com.example.movies.movies.adapters.CastCrewAdapter
import com.example.movies.movies.adapters.CrewAdapter
import com.example.movies.movies.adapters.MovieAdapter
import com.example.movies.network.*
import com.example.movies.people.adapters.PersonAdapter
import com.example.movies.people.adapters.PersonCastAdapter

private const val BASE_URL_IMG = "https://image.tmdb.org/t/p/w500"

@BindingAdapter("dataMovie")
fun bindMovie(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

@BindingAdapter("dataPeople")
fun bindPeople(recyclerView: RecyclerView, data: List<Person>?) {
    val adapter = recyclerView.adapter as PersonAdapter
    adapter.submitList(data)
}

@BindingAdapter("dataCastCrewMovie")
fun bindCastCrewMovie(recyclerView: RecyclerView, data: List<CastMovie>?) {
    val adapter = recyclerView.adapter as CastCrewAdapter
    adapter.submitList(data)
}

@BindingAdapter("dataCast")
fun bindCastMovie(recyclerView: RecyclerView, data: List<CastMovie>?) {
    val adapter = recyclerView.adapter as CastAdapter
    adapter.submitList(data)
}

@BindingAdapter("dataCrew")
fun bindCrewMovie(recyclerView: RecyclerView, data: List<CrewMovie>?) {
    val adapter = recyclerView.adapter as CrewAdapter
    adapter.submitList(data)
}

@BindingAdapter("dataKnowForPeople")
fun bindKnowForPeople(recyclerView: RecyclerView, data: List<CastPerson>?) {
    val adapter = recyclerView.adapter as PersonCastAdapter
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
    titleMovies?.forEach { it ->
        if (it != null) textView.text = textView.text.toString() + " $it,"
    }
}