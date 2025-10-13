package edu.iesam.movieslibrary.app.features.movies.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val FAVORITE_TABLE = "favorite_table"
const val FAVORITE_ID = "favorite_id"

@Entity(tableName = FAVORITE_TABLE)
class FavoriteEntity(
    @PrimaryKey @ColumnInfo(name = FAVORITE_ID) val id: String,
    @ColumnInfo(name = "favoriteMovies") val favorite: Boolean = false
)