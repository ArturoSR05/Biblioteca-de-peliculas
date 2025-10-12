package edu.iesam.movieslibrary.app.features.movies.domain

interface MovieRepository {
    suspend fun getMovies(): List<Movie>
}