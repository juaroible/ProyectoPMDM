package com.example.proyectopmdm.domain

import com.example.proyectopmdm.domain.model.Movie

/**
 * Interfaz que define los métodos para obtener y manipular datos de películas
 */
interface MovieRepository {
    suspend fun fetchMovies(): List<Movie>
}