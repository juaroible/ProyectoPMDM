package com.example.proyectopmdm.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.proyectopmdm.R
import com.example.proyectopmdm.databinding.ActivityMainBinding
import com.example.proyectopmdm.domain.model.Movie

class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels { MovieViewModel.Factory }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter = MovieListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.movieList.adapter = adapter
        viewModel.fetchMovies()
        viewModel.movies.observe(this) { movies ->
            adapter.submitList(movies)
        }

        viewModel.loading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

    }

}