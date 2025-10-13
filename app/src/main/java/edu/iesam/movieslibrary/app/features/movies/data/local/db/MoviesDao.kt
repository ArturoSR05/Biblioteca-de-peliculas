package edu.iesam.movieslibrary.app.features.movies.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Query("SELECT * FROM $MOVIES_TABLE")
    suspend fun findAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM $MOVIES_TABLE WHERE $MOVIE_ID = :id LIMIT 1")
    suspend fun findById(id: String): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllMovies(vararg movieEntity: MovieEntity)
}