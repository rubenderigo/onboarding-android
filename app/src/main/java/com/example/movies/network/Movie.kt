package com.example.movies.network

import com.squareup.moshi.Json

data class Movie(
    @Json(name = "poster_path") val imgSrcUrl: String,
    val overview: String,
    val release_date: String,
    val id: Long,
    val original_language: String,
    val title: String,
    val popularity: Float
)

data class Movies(@field:Json(name = "results") val movies: List<Movie>)


