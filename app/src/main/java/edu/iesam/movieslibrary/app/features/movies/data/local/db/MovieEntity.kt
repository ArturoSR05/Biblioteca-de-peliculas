package edu.iesam.movieslibrary.app.features.movies.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val MOVIES_TABLE = "movies"
const val MOVIE_ID = "movie_id"

@Entity(tableName = MOVIES_TABLE)
class MovieEntity(

    @PrimaryKey @ColumnInfo(name = MOVIE_ID) val id: String,
    @ColumnInfo(name = "tittle") val tittle: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo("summary") val summary: String
)