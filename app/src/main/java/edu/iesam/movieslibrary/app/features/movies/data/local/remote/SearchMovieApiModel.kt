package edu.iesam.movieslibrary.app.features.movies.data.local.remote

import com.google.gson.annotations.SerializedName

data class SearchShowApiModel(
    @SerializedName("score") val score: Float?,
    @SerializedName("show") val show: MovieApiModel
)