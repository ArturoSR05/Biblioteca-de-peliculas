package edu.iesam.movieslibrary.app

import android.app.Application
import edu.iesam.movieslibrary.app.di.AppModule
import edu.iesam.movieslibrary.app.di.RemoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module

class MoviesLibraryApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesLibraryApp)
            modules(AppModule().module, RemoteModule().module)
        }
    }
}