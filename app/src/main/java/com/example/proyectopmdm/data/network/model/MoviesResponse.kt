package com.example.proyectopmdm.data.network.model

import com.squareup.moshi.Json

data class MoviesResponse(
    val dates: Dates?,
    val page: Int = 0,
    @Json(name = "results" ) val movieList: List<MovieResponse> = emptyList(),
    @Json(name = "total_pages") val totalPages: Int = 0,
    @Json(name = "total_results") val totalResults: Int = 0
)
