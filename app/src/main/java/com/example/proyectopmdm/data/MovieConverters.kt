package com.example.proyectopmdm.data

import com.example.proyectopmdm.data.local.entity.MovieEntity
import com.example.proyectopmdm.data.network.model.MovieResponse
import com.example.proyectopmdm.domain.model.Movie

/**
 * Convierte un objeto MovieResponse en un objeto MovieEntity.
 *
 * @return El objeto convertido de tipo MovieEntity.
 */
fun MovieResponse.toDatabase(): MovieEntity =
    MovieEntity(id, title, posterPath)

/**
 * Convierte un objeto MovieEntity en un objeto Movie.
 *
 * @return El objeto convertido de tipo Movie.
 */
fun MovieEntity.toDomain(): Movie = Movie(id, title, posterPath)
