package edu.iesam.movieslibrary.app.features.movies.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.movieslibrary.app.extensions.loadUrl
import edu.iesam.movieslibrary.app.features.movies.domain.Movie
import edu.iesam.movieslibrary.databinding.ViewMoviesItemBinding

class MoviesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewMoviesItemBinding

    fun bind(movie: Movie) {
        binding = ViewMoviesItemBinding.bind(view)
        binding.apply {
            title.text = movie.title
            image.loadUrl(movie.image)
            summary.text = movie.summary
        }
    }
}