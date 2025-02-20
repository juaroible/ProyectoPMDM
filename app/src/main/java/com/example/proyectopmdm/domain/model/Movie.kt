package com.example.proyectopmdm.domain.model

/**
 * Clase que representa una película
 */
data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String
) {
    // Variable calculada para obtener la URL de la imagen
    val posterUrl: String = "https://image.tmdb.org/t/p/w500$posterPath"
}
