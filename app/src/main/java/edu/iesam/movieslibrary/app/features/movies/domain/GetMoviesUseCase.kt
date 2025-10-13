package edu.iesam.movieslibrary.app.features.movies.domain

import org.koin.core.annotation.Single

@Single
class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): List<Movie> {
        return movieRepository.getMovies()
    }
}