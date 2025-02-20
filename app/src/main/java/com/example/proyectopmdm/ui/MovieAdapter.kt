package com.example.proyectopmdm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectopmdm.R
import com.example.proyectopmdm.databinding.ItemMovieListBinding
import com.example.proyectopmdm.domain.model.Movie

/**
 * Adaptador para la lista de películas en la pantalla.
 */
class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    /**
     * ViewHolder que representa una película en la lista.
     */
    class MovieViewHolder(private val binding: ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Vincula los datos de la película al ViewHolder utilizando Glide para cargar la imagen.
         *
         * @param movie Película que se vinculará al ViewHolder.
         */
        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.title

            Glide.with(binding.moviePoster.context).load(movie.posterUrl)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(binding.moviePoster)
        }
    }

    // Lista de películas.
    private val movies = mutableListOf<Movie>()

    /**
     * Crea una nueva instancia de MovieViewHolder.
     *
     * @param parent El ViewGroup padre en el que se inflará la vista.
     * @param viewType Tipo de vista (no usado aquí).
     * @return Una nueva instancia de MovieViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieListBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    /**
     * Devuelve el número de películas en la lista.
     *
     * @return Tamaño de la lista de películas.
     */
    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * Vincula los datos de la película correspondiente al ViewHolder en la posición dada.
     *
     * @param holder El MovieViewHolder al que se vincularán los datos.
     * @param position La posición de la película en la lista.
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }
}
