package edu.iesam.movieslibrary.app.features.movies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.movieslibrary.app.features.movies.domain.GetFavoriteMoviesUseCase
import edu.iesam.movieslibrary.app.features.movies.domain.GetMoviesUseCase
import edu.iesam.movieslibrary.app.features.movies.domain.Movie
import edu.iesam.movieslibrary.app.features.movies.domain.ToggleFavoriteMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val toggleFavoriteMoviesUseCase: ToggleFavoriteMoviesUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun fetchMovies() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val movies = getMoviesUseCase.invoke()
            _uiState.postValue(
                UiState(
                    movies = movies,
                    errorApp = false
                )
            )
        }
    }

    fun loadFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val moviesFavorites = getFavoriteMoviesUseCase()
            _uiState.postValue(
                UiState(
                    movies = moviesFavorites,
                    errorApp = false
                )
            )
        }
    }

    fun toggleFavorite(movie: Movie, isFavoriteView: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            toggleFavoriteMoviesUseCase(movie)
            _uiState.value?.movies.let { currentMovies ->
                val updatedMovies = currentMovies?.mapNotNull {
                    if (it.movie.id == movie.id) {
                        val updatedAlbum = it.copy(isFavorite = !it.isFavorite)
                        if (isFavoriteView && !updatedAlbum.isFavorite) null else updatedAlbum
                    } else it
                }
                _uiState.postValue(
                    UiState(
                        movies = updatedMovies,
                        errorApp = false
                    )
                )
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: Boolean = true,
        val movies: List<GetMoviesUseCase.MovieFeed>? = null
    )
}