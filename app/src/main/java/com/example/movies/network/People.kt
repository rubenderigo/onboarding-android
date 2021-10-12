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

data class DetailPerson(
    @field:Json(name = "birthday") val birthday: String?,
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "also_known_as") val alsoKnownAs: List<String>?,
    @field:Json(name = "gender") val gender: Int?,
    @field:Json(name = "biography") val biography: String?,
    @field:Json(name = "place_of_birth") val placeOfBirth: String?,
    @Json(name = "profile_path") val profilePath: String?
)

data class CastListPerson(
    @field:Json(name = "cast") val cast: List<CastPerson>?
)

data class CastPerson(
    @field:Json(name = "credit_id") val id: String?,
    @field:Json(name = "character") val character: String?,
    @field:Json(name = "title") val title: String?,
    @Json(name = "poster_path") val posterPath: String?
)
