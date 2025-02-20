package com.example.proyectopmdm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectopmdm.data.local.entity.MovieEntity

/**
 * Interfaz que define los métodos de acceso a datos para la entidad de película
 */
@Dao
interface MovieDao {

    /**
     * Obtiene todas las películas de la base de datos
     */
    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieEntity>

    /**
     * Inserta una lista de películas en la base de datos
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(movies: List<MovieEntity>)

}