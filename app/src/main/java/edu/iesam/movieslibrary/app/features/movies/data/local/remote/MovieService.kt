package edu.iesam.movieslibrary.app.features.movies.data.local.remote

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("shows")
    suspend fun getMovies(): Response<List<MovieApiModel>>

    @GET("search/shows")
    suspend fun searchMovies(@Query("q") query: String): Response<List<SearchShowApiModel>>
}