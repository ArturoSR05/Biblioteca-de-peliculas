package edu.iesam.movieslibrary.app.features.movies.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.movieslibrary.R
import edu.iesam.movieslibrary.app.features.movies.presentation.adapter.MoviesAdapter
import edu.iesam.movieslibrary.databinding.FragmentMoviesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class MoviesFragment: Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val moviesAdapter = MoviesAdapter()
    private val viewModel: MoviesViewModel by viewModel()

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
            moviesList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            moviesList.adapter = moviesAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.fetchMovies()
    }

    private fun setupObserver() {
        val movieObserver = Observer<MoviesViewModel.UiState> {
            it.movies?.let { movies ->
                moviesAdapter.submitList(movies)
            }

            it.errorApp.let {}

            if (it.isLoading) {
                Log.d("@dev", "Loading...")
            } else {
                Log.d("@dev", "Loaded")
            }
        }

        viewModel.uiState.observe(viewLifecycleOwner, movieObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}