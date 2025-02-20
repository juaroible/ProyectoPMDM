package com.example.proyectopmdm.data.network.api

import com.example.proyectopmdm.data.network.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Interfaz que define los métodos para obtener datos de películas desde la API
 */
interface MovieService {

    /**
     * Obtiene la lista de películas populares (añade movie/popular al endpoint de la API)
     */
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): MoviesResponse

}