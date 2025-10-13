package edu.iesam.movieslibrary.app.features.movies.domain

import org.koin.core.annotation.Single

@Single
class DeleteFavoriteMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movie: Movie) {
        movieRepository.deleteFavorites(movie)
    }
}