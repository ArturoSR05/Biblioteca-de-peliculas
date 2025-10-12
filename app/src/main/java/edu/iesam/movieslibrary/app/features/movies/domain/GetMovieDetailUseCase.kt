package edu.iesam.movieslibrary.app.features.movies.domain

import org.koin.core.annotation.Single

@Single
class GetMovieDetailUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(id: String): Movie {
        return movieRepository.getMovieById(id)
    }
}