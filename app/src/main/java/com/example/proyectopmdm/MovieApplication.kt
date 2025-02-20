package com.example.proyectopmdm

import android.app.Application
import androidx.room.Room
import com.example.proyectopmdm.data.MovieRepositoryImpl
import com.example.proyectopmdm.data.local.MovieDatabase
import com.example.proyectopmdm.data.network.NetworkProvider
import com.example.proyectopmdm.domain.MovieRepository

/**
 * Clase que representa la aplicación de películas y proporciona una instancia de MovieRepository
 */
class MovieApplication: Application() {

    // Token de autorización para acceder a la API (correo de clase)
    private val API_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMDVmZjY3OTRkNmVhYTVmMDk0ZmE3OWI0MDA2ZTc4ZCIsIm5iZiI6MTczODA3NjYyNS40MjMsInN1YiI6IjY3OThmMWQxYjkyMWM1NDhkODJhZmI0ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Ynq8FtjXR1ZXuGh9ThIPXZ2Njvq68VJH9j5lgdlo_es"

    // Propiedad para acceder a la instancia de MovieRepository
    lateinit var movieRepository: MovieRepository

    /**
     * Método llamado cuando se crea la aplicación para inicializar la instancia de MovieRepository
     */
    override fun onCreate() {
        super.onCreate()

        // Crea una instancia de MovieRepository utilizando MovieRepositoryImpl y MovieDatabase
        val database = Room.databaseBuilder(applicationContext, MovieDatabase::class.java,
            "movie-database").build()

        // Crea una instancia de MovieRepositoryImpl utilizando NetworkProvider y MovieDatabase
        // y la asigna a la propiedad movieRepository
        movieRepository = MovieRepositoryImpl(
            NetworkProvider(API_TOKEN).getMovieService(), database.movieDao()
        )

    }

}