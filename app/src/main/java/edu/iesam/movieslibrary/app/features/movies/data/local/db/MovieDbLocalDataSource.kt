package edu.iesam.movieslibrary.app.features.movies.data.local.db

import edu.iesam.movieslibrary.app.features.movies.domain.Movie
import org.koin.core.annotation.Single
import kotlin.collections.map

@Single
class MovieDbLocalDataSource(
    private val moviesDao: MoviesDao,
    private val favoriteDao: FavoriteDao) {

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

    suspend fun getFavoriteMovies(): List<Movie> {
        val favoriteEntity = favoriteDao.getFavorites()
        val favoriteMovies = favoriteEntity.map {
            moviesDao.findById(it.id).toDomain()
        }
        return favoriteMovies
    }

    suspend fun saveFavorite(movie: Movie) {
        val favoriteEntity = FavoriteEntity(movie.id, favorite = true)
        favoriteDao.addFavorite(favoriteEntity)
    }

    suspend fun deleteFavorite(movie: Movie) {
        val favoriteEntity = FavoriteEntity(movie.id, favorite = true)
        favoriteDao.deleteFavorite(favoriteEntity)
    }

    suspend fun toggleFavorite(movie: Movie) {
        val favoriteEntity = favoriteDao.findFavoriteById(movie.id)
        if (favoriteEntity == null) {
            saveFavorite(movie)
        } else {
            deleteFavorite(movie)
        }
    }
}