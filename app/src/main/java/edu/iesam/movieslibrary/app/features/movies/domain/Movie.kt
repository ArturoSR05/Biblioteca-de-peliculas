package edu.iesam.movieslibrary.app.features.movies.domain

data class Movie (

    val id: String,
    val title: String,
    var image: String,
    val summary: String,
    val isFavorite: Boolean = false
)