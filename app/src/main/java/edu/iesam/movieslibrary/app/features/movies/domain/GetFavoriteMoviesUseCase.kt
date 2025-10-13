package edu.iesam.movieslibrary.app.features.movies.domain

import org.koin.core.annotation.Single

@Single
class GetFavoriteMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(): List<GetMoviesUseCase.MovieFeed> {
        return movieRepository.getFavorites().map {
            GetMoviesUseCase.MovieFeed(
                it, true
            )
        }
    }
}