package com.example.proyectopmdm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectopmdm.data.local.dao.MovieDao
import com.example.proyectopmdm.data.local.entity.MovieEntity

/**
 * Clase que representa la base de datos de películas utilizando Room
 */
@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    /**
     * Obtiene el DAO para la entidad de película
     */
    abstract fun movieDao(): MovieDao

}