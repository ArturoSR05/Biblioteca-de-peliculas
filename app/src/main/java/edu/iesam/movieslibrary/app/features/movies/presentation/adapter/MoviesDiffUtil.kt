package edu.iesam.movieslibrary.app.features.movies.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import edu.iesam.movieslibrary.app.features.movies.domain.GetMoviesUseCase
import edu.iesam.movieslibrary.app.features.movies.domain.Movie

class MoviesDiffUtil : DiffUtil.ItemCallback<GetMoviesUseCase.MovieFeed>() {

    override fun areItemsTheSame(oldItem: GetMoviesUseCase.MovieFeed, newItem: GetMoviesUseCase.MovieFeed): Boolean {
        return oldItem.movie.id == newItem.movie.id
    }

    override fun areContentsTheSame(oldItem: GetMoviesUseCase.MovieFeed, newItem: GetMoviesUseCase.MovieFeed): Boolean {
        return oldItem == newItem
    }
}