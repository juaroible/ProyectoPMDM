package com.example.proyectopmdm.data.network

import com.example.proyectopmdm.data.network.api.MovieService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Clase que proporciona una instancia de Retrofit con un token de autorización
 */
class NetworkProvider(private val token: String) {

    // Propiedad privada para almacenar la instancia de Retrofit
    private var retrofit: Retrofit? = null

    /**
     * Obtiene una instancia de Retrofit con un token de autorización
     *
     * @return Instancia de Retrofit
     */
    fun getRetrofit(): Retrofit {
        // Si la instancia de Retrofit no ha sido inicializada, la crea
        if (retrofit == null) {
            // Crea un cliente OkHttpClient con un interceptor que agrega el token de autorización
            val client = OkHttpClient.Builder()
                // Agrega un interceptor que agrega el token de autorización a la solicitud
                .addNetworkInterceptor(RequestTokenInterceptor(token))
                .build()

            // Crea una instancia de Moshi con un adaptador Kotlin
            val moshi = Moshi.Builder()
                // Agrega un adaptador para manejar clases de Kotlin
                .addLast(KotlinJsonAdapterFactory())
                .build()

            // Crea una instancia de Retrofit con la URL base, el cliente OkHttpClient y el
            // convertidor Moshi
            retrofit = Retrofit.Builder()
                // URL base de la API
                .baseUrl("https://api.themoviedb.org/3/")
                // Cliente OkHttpClient creado anteriormente
                .client(client)
                // Convertidor Moshi para manejar las respuestas JSON como objetos Kotlin
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        // Devuelve la instancia de Retrofit, el !! indica que retrofit no puede ser nulo
        return retrofit!!

    }

    fun getMovieService(): MovieService {
        return getRetrofit().create(MovieService::class.java)
    }

}