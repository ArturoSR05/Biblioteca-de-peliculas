package edu.iesam.movieslibrary.app.features.movies.data.local.remote

import com.google.gson.annotations.SerializedName

data class MovieApiModel (

    @SerializedName("id") val id: String,
    @SerializedName("name") val title: String,
    @SerializedName("image") val image: Image? = null,
    @SerializedName("summary") val summary: String? = null
) {
    data class Image(
        val medium: String? = null,
        val original: String? = null
    )
}