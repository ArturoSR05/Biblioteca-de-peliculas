package edu.iesam.movieslibrary.app.features.movies.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.movieslibrary.app.features.movies.domain.Movie

class MoviesDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}