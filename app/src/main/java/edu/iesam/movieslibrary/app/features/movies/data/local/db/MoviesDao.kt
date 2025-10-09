package edu.iesam.movieslibrary.app.features.movies.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Query("SELECT * FROM $MOVIES_TABLE")
    suspend fun findAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllMovies(vararg movieEntity: MovieEntity)
}