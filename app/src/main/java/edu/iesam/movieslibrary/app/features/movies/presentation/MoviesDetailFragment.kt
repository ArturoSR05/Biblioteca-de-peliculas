package edu.iesam.movieslibrary.app.features.movies.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.iesam.movieslibrary.R
import edu.iesam.movieslibrary.databinding.FragmentMoviesDetailBinding

class MoviesDetailFragment: Fragment() {

    private var _binding: FragmentMoviesDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            moviesDetailToolbar.toolbar.title = requireContext().getString(R.string.details)
        }
    }
}