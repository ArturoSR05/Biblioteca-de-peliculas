package edu.iesam.movieslibrary.app.features.movies.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.iesam.movieslibrary.R
import edu.iesam.movieslibrary.databinding.FragmentMoviesBinding

class MoviesFragment: Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            moviesToolbar.toolbar.title = requireContext().getString(R.string.movies)
            moviesToolbar.imgToolbar.setImageResource(R.drawable.ic_favorite_border)
        }
    }
}