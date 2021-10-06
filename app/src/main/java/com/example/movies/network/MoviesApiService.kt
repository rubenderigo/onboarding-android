package com.example.movies.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "dc9f006f86919b1c3de8a839e461ad59"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MoviesApiService {

    @GET("movie/popular?api_key=${API_KEY}")
    suspend fun getPopularMovies(): Movies

    @GET("movie/top_rated?api_key=${API_KEY}")
    suspend fun getTopRatedMovies(): Movies

    @GET("movie/{movie_id}?api_key=${API_KEY}")
    suspend fun getDetailMovie(@Path("movie_id") movie_id: Int?): Movie

    @GET("movie/{movie_id}/credits?api_key=${API_KEY}")
    suspend fun getCreditsMovie(@Path("movie_id") movie_id: Int?): DetailMovie

    @GET("person/popular?api_key=${API_KEY}")
    suspend fun getPopularPeople(): People

    @GET("person/{person_id}?api_key=${API_KEY}")
    suspend fun getDetailPerson(@Path("person_id") person_id: Int?): DetailPerson

    @GET("person/{person_id}/movie_credits?api_key=${API_KEY}")
    suspend fun getCastPerson(@Path("person_id") person_id: Int?): CastListPerson
}

object MoviesApi {

    val retrofitService: MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}
