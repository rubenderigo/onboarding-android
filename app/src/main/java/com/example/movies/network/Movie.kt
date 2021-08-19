package com.example.movies.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movies(
    @field:Json(name = "results") val movies: List<Movie>?
)

data class Movie(
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "adult") val adult: Boolean?,
    @Json(name = "overview")val overview: String?,
    @Json(name = "release_date")val releaseDate: String?,
    @Json(name = "genre_ids") val genreIds: List<Int>?,
    @Json(name = "id")val id: Long?,
    @Json(name = "original_title") val originalTitle: String?,
    @Json(name = "original_language")val originalLanguage: String?,
    @Json(name = "title")val title: String?,
    @Json(name = "backdrop_path") val backdropPath: String? = "",
    @Json(name = "popularity")val popularity: Float?,
    @Json(name = "vote_count") val voteCount: Long?,
    @Json(name = "video") val video: Boolean?,
    @Json(name = "vote_average") val voteAverage: Float?
)

data class DetailMovie (
    @Json(name = "id") val id: Int?,
    @Json(name = "cast") val cast: List<CastMovie>?,
    @Json(name = "crew") val crew: List<CrewMovie>?
)

data class CastMovie(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "name") val name: String?,
    @Json(name = "profile_path") val profilePath: String?,
    @field:Json(name = "character") val character: String?
)

data class CrewMovie(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "department") val department: String?
)
