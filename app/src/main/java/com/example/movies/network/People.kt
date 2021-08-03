package com.example.movies.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class People(
    @field:Json(name = "results") val people: List<Person>?
)

data class Person(
    @Json(name = "adult") val adult: Boolean?,
    @Json(name = "gender") val gender: String?,
    @Json(name = "id") val id: Integer?,
    @Json(name = "known_for") val knownFor: List<Movie>?,
    @Json(name = "known_for_departament") val knownForDepartament: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "popularity") val popularity: Float?,
    @Json(name = "profile_path") val profilePath: String?
)
