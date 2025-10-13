package edu.iesam.movieslibrary.app.features.movies.presentation.adapter

import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.movieslibrary.R
import edu.iesam.movieslibrary.app.extensions.loadUrl
import edu.iesam.movieslibrary.app.features.movies.domain.GetMoviesUseCase
import edu.iesam.movieslibrary.app.features.movies.domain.Movie
import edu.iesam.movieslibrary.app.features.movies.presentation.MoviesFragmentDirections
import edu.iesam.movieslibrary.databinding.ViewMoviesItemBinding

class MoviesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewMoviesItemBinding

    fun bind(
        movieFeed: GetMoviesUseCase.MovieFeed,
        onClick: ((Movie) -> Unit)?
    ) {
        binding = ViewMoviesItemBinding.bind(view)
        binding.apply {
            title.text = movieFeed.movie.title
            image.loadUrl(movieFeed.movie.image)
            summary.text = movieFeed.movie.summary
            moviesItem.setOnClickListener {
                navigateToDetail(movieFeed.movie.id)
            }
            favorite.setImageResource(
                if (movieFeed.isFavorite) R.drawable.ic_favorite
                else R.drawable.ic_favorite_border
            )
            onClick?.let {
                favorite.setOnClickListener {
                    onClick.invoke(movieFeed.movie)
                }
            }
        }
    }

    private fun navigateToDetail(movieId: String) {
        findNavController(view).navigate(
            MoviesFragmentDirections.actionFromMoviesToMoviesDetail(movieId = movieId)
        )
    }
}