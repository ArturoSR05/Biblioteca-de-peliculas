package edu.iesam.movieslibrary.app.di

import android.content.Context
import androidx.room.Room
import edu.iesam.movieslibrary.app.features.movies.data.local.db.MoviesLibraryDataBase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class LocalModule {

    @Single
    fun provideDataBase(context: Context): MoviesLibraryDataBase {
        val db = Room.databaseBuilder(
            context,
            MoviesLibraryDataBase::class.java,
            "movies-library-db"
        )
        db.fallbackToDestructiveMigration()
        return db.build()
    }
}