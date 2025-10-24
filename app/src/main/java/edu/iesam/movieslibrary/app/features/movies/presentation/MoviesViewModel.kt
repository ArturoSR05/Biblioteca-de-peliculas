package edu.iesam.movieslibrary.app.features.movies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.movieslibrary.app.features.movies.domain.GetFavoriteMoviesUseCase
import edu.iesam.movieslibrary.app.features.movies.domain.GetMoviesUseCase
import edu.iesam.movieslibrary.app.features.movies.domain.Movie
import edu.iesam.movieslibrary.app.features.movies.domain.SearchMoviesUseCase
import edu.iesam.movieslibrary.app.features.movies.domain.ToggleFavoriteMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val toggleFavoriteMoviesUseCase: ToggleFavoriteMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    private var query: String = ""
    private var searchJob: Job? = null

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

    fun onSearchQueryChanged(text: String) {
        query = text
        searchJob?.cancel()
        if (text.isBlank()) {
            // Volver a “todos” si se borra el texto
            loadAll()
            return
        }
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(_uiState.value?.copy(isLoading = true, errorApp = false))
            try {
                val feed = searchMoviesUseCase(query)
                _uiState.postValue(
                    _uiState.value?.copy(
                        movies = feed,
                        isLoading = false,
                        errorApp = false
                    )
                )
            } catch (e: Exception) {
                _uiState.postValue(_uiState.value?.copy(isLoading = false, errorApp = true))
            }
        }
    }

    fun submitSearch() {
        onSearchQueryChanged(query)
    }

    fun clearSearch() {
        query = ""
        loadAll()
    }

    private fun loadAll() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(_uiState.value?.copy(isLoading = true))
            try {
                val feed = getMoviesUseCase()
                _uiState.postValue(_uiState.value?.copy(movies = feed, isLoading = false, errorApp = false))
            } catch (_: Exception) {
                _uiState.postValue(_uiState.value?.copy(isLoading = false, errorApp = true))
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: Boolean = true,
        val movies: List<GetMoviesUseCase.MovieFeed>? = null
    )
}