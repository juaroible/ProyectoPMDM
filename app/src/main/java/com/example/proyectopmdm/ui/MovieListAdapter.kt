package com.example.proyectopmdm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectopmdm.R
import com.example.proyectopmdm.domain.model.Movie

/**
 * Adaptador para la lista de películas en la pantalla
 */
class MovieListAdapter: ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    /**
     * ViewHolder para la lista de películas
     */
    class MovieViewHolder(private val parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
    ) {
        // Titutlo de la pelicula
        private val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        // Poster de la pelicula
        private val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)

        /**
         * Vincula los datos de la pelicula al viewholder, usa Glide para cargar la imagen
         */
        fun bindTo(movie: Movie) {
            movieTitle.text = movie.title
            Glide.with(parent.context).load(movie.posterUrl).placeholder(R.mipmap.ic_launcher)
                .fitCenter().into(moviePoster)
        }
    }

    // Comparador para la lista de películas
    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
        }
    }

    /**
     * Crea un viewholder para la lista de peliculas
     *
     * @return MovieViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent)
    }

    /**
     * Vincula los datos de la pelicula al viewholder
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

}