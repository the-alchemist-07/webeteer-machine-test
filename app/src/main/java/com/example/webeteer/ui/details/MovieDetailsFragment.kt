package com.example.webeteer.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.example.webeteer.R
import com.example.webeteer.databinding.FragmentMovieDetailsBinding
import com.example.webeteer.domain.model.Movie
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)

        initData(viewModel.movieData)
        setListeners()
    }

    private fun initData(movieData: Movie?) = binding.apply {
        movieData?.let { movie ->
            tvMovieTitle.text = movie.title
            tvMovieDescription.text = movie.description

            // Set the rating details
            if (movie.rating.isNotBlank())
                tvRating.text = movie.rating
            else groupRating.isVisible = false

            // Set the release date
            if (movie.releaseDate.isNotBlank())
                tvReleaseDate.text = movie.releaseDate
            else tvReleaseDate.isVisible = false

            // Set the genres
            movie.genreList.map { genre ->
                chipGroupGenres.addView(createTagChip(genre))
            }

            // Loading the poster image
            binding.ivPoster.load(movieData.posterUrl) {
                crossfade(true)
                transformations(RoundedCornersTransformation(0f, 0f, 24f, 24f))
                listener(
                    onError = { _, _ ->
                        // If failed to load the given image, this image will be set by default
                        binding.ivPoster.load(R.drawable.img_john_wick_poster) {
                            crossfade(true)
                            transformations(RoundedCornersTransformation(0f, 0f, 24f, 24f))
                        }
                    }
                )
            }
        }
    }

    private fun setListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun createTagChip(chipName: String): Chip {
        return Chip(requireContext()).apply {
            text = chipName
            setChipBackgroundColorResource(R.color.red_100)
            setTextColor(ContextCompat.getColor(context, R.color.red))
        }
    }
}