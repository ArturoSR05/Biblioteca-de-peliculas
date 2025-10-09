package edu.iesam.movieslibrary.app.features.movies.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class MoviesLibraryDataBase: RoomDatabase(){
    abstract fun moviesDao(): MoviesDao
}