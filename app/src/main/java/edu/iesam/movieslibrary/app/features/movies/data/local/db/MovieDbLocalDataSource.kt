package edu.iesam.movieslibrary.app.features.movies.data.local.db

import edu.iesam.movieslibrary.app.features.movies.domain.Movie
import org.koin.core.annotation.Single
import kotlin.collections.map

@Single
class MovieDbLocalDataSource(private val moviesDao: MoviesDao) {

    suspend fun findAll(): List<Movie> {
        val movies = moviesDao.findAllMovies()
        return if (movies.isEmpty()) {
            emptyList()
        } else (movies.map { it.toDomain() })
    }

    suspend fun saveAll(movies: List<Movie>) {
        val moviesList = movies.map {
            it.toEntity()
        }
        moviesDao.saveAllMovies(*moviesList.toTypedArray())
    }

    suspend fun getById(id: String): Movie {
        return moviesDao.findById(id).toDomain()
    }
}