package edu.iesam.movieslibrary.app.features.movies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.movieslibrary.app.features.movies.domain.GetMovieDetailUseCase
import edu.iesam.movieslibrary.app.features.movies.domain.Movie
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    fun loadMovie(movieId: String) {
        viewModelScope.launch {
            val data = getMovieDetailUseCase(movieId)
            _movie.postValue(data)
        }
    }
}