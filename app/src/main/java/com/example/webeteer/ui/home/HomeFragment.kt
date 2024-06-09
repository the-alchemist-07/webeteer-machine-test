package com.example.webeteer.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.webeteer.R
import com.example.webeteer.databinding.FragmentHomeBinding
import com.example.webeteer.domain.model.Genre
import com.example.webeteer.ui.home.adapters.GenreAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val genreAdapter by lazy { GenreAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        setupRecycler()
        observeState()
    }

    private fun setupRecycler() {
        binding.recyclerGenres.adapter = genreAdapter
    }

    private fun observeState() = viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.homeState.collect { state ->
                when (state) {
                    is HomeState.Loading -> showLoader(true)
                    is HomeState.SuccessGenreList -> handleGenreList(state.genreList)
                    is HomeState.Error -> showError(state.message)
                    else -> Unit
                }
            }
        }
    }

    private fun showLoader(flag: Boolean) {
        binding.progressBar.isVisible = flag
    }

    private fun handleGenreList(genreList: List<Genre>) {
        showLoader(false)
        genreAdapter.submitList(genreList)
    }

    private fun showError(message: String) {
        showLoader(false)
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}