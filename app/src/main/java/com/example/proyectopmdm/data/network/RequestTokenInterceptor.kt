package com.example.proyectopmdm.data.network

import okhttp3.Interceptor
import okhttp3.Response

class RequestTokenInterceptor(private val token: String): Interceptor {

    /**
     * Intercepta la solicitud y agrega el token de autorización a la cabecera
     *
     * @param chain La cadena de interceptores
     *
     * @return La respuesta de la solicitud
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        // Obtiene la solicitud actual
        val request = chain.request()

        // Crea una nueva solicitud con el token de autorización agregado
        val newRequest = request.newBuilder()
            // Agrega el token de autorización a la cabecera
            .addHeader("Authorization", "Bearer $token")
            // Agrega el tipo de contenido y el formato de la respuesta
            .addHeader("Accept", "application/json")
            .build()

        // Continúa con la solicitud modificada
        val response = chain.proceed(newRequest)

        return response
    }

}