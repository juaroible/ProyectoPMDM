package com.example.proyectopmdm.ui

import android.content.Intent
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

    // Instancia del ViewModel
    private val viewModel: MovieViewModel by viewModels { MovieViewModel.Factory }
    // Binding
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    // Instancia del adaptador
    private val adapter = MovieListAdapter { movie -> openMovieDetails(movie) }

    // Constantes para los nombres de las claves de los extras
    companion object {
        const val MOVIE_TITLE = "title"
        const val MOVIE_POSTER = "poster"
    }

    /**
     * Método llamado cuando se crea la actividad
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuración del RecyclerView
        binding.movieList.adapter = adapter
        viewModel.fetchMovies()
        viewModel.movies.observe(this) { movies ->
            adapter.submitList(movies)
        }

        // Configuración del ProgressBar
        viewModel.loading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

    }

    /**
     * Abre la actividad de detalles de la película
     *
     * @param movie Película seleccionada
     */
    private fun openMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetail::class.java)
        intent.putExtra(MOVIE_TITLE, movie.title)
        intent.putExtra(MOVIE_POSTER, movie.posterUrl)

        // Inicia la actividad con el intent
        startActivity(intent)
    }

}