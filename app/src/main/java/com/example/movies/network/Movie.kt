package com.example.movies.network

import com.squareup.moshi.Json

data class Movie (
    val id: Long,
    val title: String,
    @Json (name = "poster_path") val imgSrcUrl: String,
    val overview: String,
    val release_date: String,
    val original_language: String,
    val popularity: Float
)