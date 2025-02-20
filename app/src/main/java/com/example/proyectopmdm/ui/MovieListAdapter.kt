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
class MovieListAdapter(private val onClick: (Movie) -> Unit) : ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    class MovieViewHolder(private val parent: ViewGroup, private val onClick: (Movie) -> Unit): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
    ) {
        private val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        private val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)

        fun bindTo(movie: Movie) {
            movieTitle.text = movie.title
            Glide.with(parent.context).load(movie.posterUrl).placeholder(R.mipmap.ic_launcher)
                .fitCenter().into(moviePoster)

            // OnClickListener
            itemView.setOnClickListener { onClick(movie) }
        }
    }

    // Método de fábrica para crear instancias de MovieViewHolder
    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
        }
    }

    // Método para crear una nueva instancia de MovieViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent, onClick)
    }

    // Método para vincular los datos de una película a un MovieViewHolder
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}