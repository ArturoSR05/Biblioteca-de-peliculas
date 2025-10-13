package edu.iesam.movieslibrary.app.features.movies.domain

import org.koin.core.annotation.Single

@Single
class GetMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(): List<MovieFeed> {

        val movies = movieRepository.getMovies()
        val favoriteMovies = movieRepository.getFavorites()
        val movieFeed = mutableListOf<MovieFeed>()
        movies.forEach { movie ->
            movieFeed.add(
                MovieFeed(
                    movie,
                    favoriteMovies.find {
                        it.id == movie.id
                    } != null
                )
            )
        }

        return movieFeed
    }

    data class MovieFeed(
        val movie: Movie,
        val isFavorite: Boolean
    )
}