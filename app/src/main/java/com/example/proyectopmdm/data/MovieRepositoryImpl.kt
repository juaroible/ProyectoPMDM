package com.example.proyectopmdm.data

import android.content.Context
import android.util.Log
import com.example.proyectopmdm.data.local.dao.MovieDao
import com.example.proyectopmdm.data.network.api.MovieService
import com.example.proyectopmdm.domain.MovieRepository
import com.example.proyectopmdm.domain.model.Movie

/**
 * Implementación de la interfaz MovieRepository
 *
 * @param movieService Servicio para obtener las películas
 * @param movieDao Dao para acceder a la base de datos
 */
class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val movieDao: MovieDao
): MovieRepository {

    /**
     * Obtiene la lista de películas de la API y la almacena en la base de datos
     *
     * @return Lista de películas o emptyList() en caso de error
     */
    override suspend fun fetchMovies(): List<Movie> {
        try {
            val moviesResponse = movieService.getPopularMovies()
            val movieEntities = moviesResponse.movieList.map { it.toDatabase() }
            movieDao.insertAll(movieEntities)

            return movieDao.getAllMovies().map { it.toDomain() }
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching movies", e)
        }

        return emptyList()
    }

}