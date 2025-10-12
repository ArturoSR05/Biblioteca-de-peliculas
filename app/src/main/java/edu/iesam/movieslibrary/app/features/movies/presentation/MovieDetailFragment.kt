package edu.iesam.movieslibrary.app.features.movies.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.iesam.movieslibrary.R
import edu.iesam.movieslibrary.app.extensions.loadUrl
import edu.iesam.movieslibrary.databinding.FragmentMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment: Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            moviesDetailToolbar.toolbar.title = requireContext().getString(R.string.details)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getString("movieId") ?: return
        viewModel.loadMovie(movieId)

        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            binding.tvTitle.text = movie.title
            binding.tvOverview.text = movie.summary
            binding.ivPoster.loadUrl(movie.image)
        }
    }
}