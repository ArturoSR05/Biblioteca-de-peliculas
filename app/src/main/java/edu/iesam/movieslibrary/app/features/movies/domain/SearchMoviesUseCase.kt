package edu.iesam.movieslibrary.app.features.movies.domain

import org.koin.core.annotation.Single

@Single
class SearchMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(query: String): List<GetMoviesUseCase.MovieFeed> {
        val results = repository.searchMovies(query)
        val favoriteMovies = repository.getFavorites()
        return results.map { movie ->
            GetMoviesUseCase.MovieFeed(
                movie,
                favoriteMovies.any { it.id == movie.id }
            )
        }
    }
}