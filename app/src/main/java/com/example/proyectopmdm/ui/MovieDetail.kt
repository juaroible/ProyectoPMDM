package com.example.proyectopmdm.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.proyectopmdm.R
import com.example.proyectopmdm.databinding.ActivityMovieDetailBinding

class MovieDetail : AppCompatActivity() {

    //
    lateinit var binding: ActivityMovieDetailBinding

    /**
     * Método llamado cuando se crea la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener los datos de la película de los extras del intent
        val movieTitle: String? = intent.getStringExtra(MainActivity.MOVIE_TITLE)

        // Obtener la imagen de la película de los extras del intent
        val moviePoster: String? = intent.getStringExtra(MainActivity.MOVIE_POSTER)

        // Mostrar el título de la película en la pantalla
        binding.movieDetailTitle.text = movieTitle

        // Mostrar la imagen de la película en la pantalla
        Glide.with(this).load(moviePoster)
            .placeholder(R.mipmap.ic_launcher)
            .fitCenter()
            .into(binding.movieDetailPoster)

    }
}