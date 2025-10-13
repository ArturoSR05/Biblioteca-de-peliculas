package edu.iesam.movieslibrary.app.features.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.movieslibrary.R
import edu.iesam.movieslibrary.app.features.movies.domain.GetMoviesUseCase
import edu.iesam.movieslibrary.app.features.movies.domain.Movie

class MoviesAdapter : ListAdapter<GetMoviesUseCase.MovieFeed, MoviesViewHolder>(MoviesDiffUtil()) {

    private var onItemClick: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_movies_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}