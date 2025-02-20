package com.example.proyectopmdm.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Clase que representa una entidad de película en la base de datos
 */
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    @ColumnInfo(name="poster_path") val posterPath: String
)
