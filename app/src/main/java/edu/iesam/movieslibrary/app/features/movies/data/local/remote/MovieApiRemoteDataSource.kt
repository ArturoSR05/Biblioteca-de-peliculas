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
}