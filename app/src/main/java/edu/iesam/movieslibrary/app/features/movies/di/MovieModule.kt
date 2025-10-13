package edu.iesam.movieslibrary.app.features.movies.di

import edu.iesam.movieslibrary.app.features.movies.data.local.db.MoviesDao
import edu.iesam.movieslibrary.app.features.movies.data.local.db.MoviesLibraryDataBase
import edu.iesam.movieslibrary.app.features.movies.data.local.remote.MovieService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit

@Module
@ComponentScan
class MovieModule {
    @Single
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Single
    fun provideMoviesDao(db: MoviesLibraryDataBase): MoviesDao {
        return db.moviesDao()
    }
}