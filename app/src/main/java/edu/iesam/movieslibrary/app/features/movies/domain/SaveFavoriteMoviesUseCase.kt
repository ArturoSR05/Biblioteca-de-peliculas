package edu.iesam.movieslibrary.app.features.movies.domain

import org.koin.core.annotation.Single

@Single
class SaveFavoriteMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movie: Movie) {
        movieRepository.saveFavorites(movie)
    }
}