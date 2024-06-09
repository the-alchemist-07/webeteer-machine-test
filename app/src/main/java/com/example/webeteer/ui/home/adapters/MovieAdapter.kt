package com.example.webeteer.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.example.webeteer.R
import com.example.webeteer.databinding.ItemMovieBinding
import com.example.webeteer.domain.model.Movie

class MovieAdapter(
    private val listener: OnClickListener
) : ListAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallback) {

    interface OnClickListener {
        fun onMovieClicked(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).bind(getItem(position))
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieData: Movie) {
            binding.tvMovie.text = movieData.title
            // Loading the image using coil library
            binding.ivPoster.load(movieData.posterUrl) {
                crossfade(true)
                transformations(RoundedCornersTransformation(24f))
                listener(
                    onError = { _, _ ->
                        // If failed to load the given image, this image will be set by default
                        binding.ivPoster.load(R.drawable.img_john_wick_poster) {
                            crossfade(true)
                            transformations(RoundedCornersTransformation(24f))
                        }
                    }
                )
            }

            binding.root.setOnClickListener {
                listener.onMovieClicked(movieData)
            }
        }
    }
}

object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}