package edu.iesam.movieslibrary.app.features.movies.data

import edu.iesam.movieslibrary.app.features.movies.data.local.db.MovieDbLocalDataSource
import edu.iesam.movieslibrary.app.features.movies.data.local.remote.MovieApiRemoteDataSource
import edu.iesam.movieslibrary.app.features.movies.domain.Movie
import edu.iesam.movieslibrary.app.features.movies.domain.MovieRepository
import org.koin.core.annotation.Single

@Single
class MoviesDataRepository(
    private val local: MovieDbLocalDataSource,
    private val remote: MovieApiRemoteDataSource
) : MovieRepository {

    override suspend fun getMovies(): List<Movie> {
        val moviesLocal = local.findAll()
        if (moviesLocal.isEmpty()) {
            val moviesRemote = remote.getMovies()
            local.saveAll(moviesRemote)
            return moviesRemote
        }
        return moviesLocal
    }

    override suspend fun getMovieById(id: String): Movie {
        return local.getById(id)
    }
}