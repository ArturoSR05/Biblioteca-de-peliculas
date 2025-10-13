package edu.iesam.movieslibrary.app.features.movies.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM $FAVORITE_TABLE WHERE favoriteMovies = 1")
    suspend fun getFavorites(): List<FavoriteEntity>

    @Query("SELECT * FROM $FAVORITE_TABLE WHERE favorite_id= :favoriteId LIMIT 1")
    suspend fun findFavoriteById(favoriteId: String): FavoriteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(vararg favorite: FavoriteEntity)

    @Update
    suspend fun updateFavorite(vararg favorite: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(vararg favorite: FavoriteEntity)
}