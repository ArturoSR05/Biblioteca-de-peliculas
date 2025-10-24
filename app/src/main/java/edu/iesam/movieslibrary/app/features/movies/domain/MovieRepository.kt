package edu.iesam.movieslibrary.app.features.movies.domain

interface MovieRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun getMovieById(id: String): Movie
    suspend fun getFavorites(): List<Movie>
    suspend fun saveFavorites(movie: Movie)
    suspend fun deleteFavorites(movie: Movie)
    suspend fun toggleFavorites(movie: Movie)
    suspend fun searchMovies(query: String): List<Movie>
}