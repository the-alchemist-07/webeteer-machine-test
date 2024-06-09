package com.example.webeteer.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.webeteer.databinding.ItemGenreBinding
import com.example.webeteer.domain.model.Genre

class GenreAdapter(
    private val listener: MovieAdapter.OnClickListener
) : ListAdapter<Genre, RecyclerView.ViewHolder>(GenreDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GenreViewHolder).bind(getItem(position))
    }

    inner class GenreViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genreData: Genre) {
            binding.tvGenre.text = genreData.genre

            val adapter = MovieAdapter(listener)
            binding.recyclerMovies.adapter = adapter
            adapter.submitList(genreData.movieList)
        }
    }
}

object GenreDiffCallback : DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }
}