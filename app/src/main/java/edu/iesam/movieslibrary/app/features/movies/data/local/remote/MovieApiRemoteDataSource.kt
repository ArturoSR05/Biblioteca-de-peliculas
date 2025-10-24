package edu.iesam.movieslibrary.app.features.movies.data.local.remote

import edu.iesam.movieslibrary.app.features.movies.domain.Movie
import org.koin.core.annotation.Single

@Single
class MovieApiRemoteDataSource(private val movieService: MovieService) {

    suspend fun getMovies(): List<Movie> {
        val response = movieService.getMovies()

        return if (response.isSuccessful) {
            val moviesList = response.body()?.map { it.toModel() } ?: emptyList()
            moviesList.shuffled()
        } else {
            emptyList()
        }
    }

    suspend fun searchMovies(query: String): List<Movie> {
        if (query.isBlank()) return emptyList()
        val response = movieService.searchMovies(query)
        return if (response.isSuccessful) {
            // La API envuelve el show en { score, show }
            response.body()?.map { it.show.toModel() } ?: emptyList()
        } else {
            emptyList()
        }
    }
}