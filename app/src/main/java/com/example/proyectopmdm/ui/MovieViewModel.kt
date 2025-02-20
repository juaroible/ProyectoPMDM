package com.example.proyectopmdm.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.proyectopmdm.MovieApplication
import com.example.proyectopmdm.domain.MovieRepository
import com.example.proyectopmdm.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla de películas
 */
class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    // Propiedades para los datos de la pantalla
    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: MutableLiveData<List<Movie>> get() = _movies

    // Propiedad para el estado de carga
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: MutableLiveData<Boolean> get() = _loading

    /**
     * Inicializa la pantalla de películas
     */
    fun fetchMovies() {
        // _loading.value = true indica que se está cargando los datos
        _loading.value = true
        // viewModelScope.launch(Dispatchers.IO) lanza una corrutina en un hilo de entrada/salida
        viewModelScope.launch(Dispatchers.IO) {
            // _movies.postValue(movieRepository.fetchMovies()) actualiza los datos de la pantalla
            _movies.postValue(movieRepository.fetchMovies())
            // _loading.postValue(false) indica que se ha terminado de cargar los datos
            _loading.postValue(false)
        }
    }

    // Método de fábrica para crear instancias de MovieViewModel
    companion object {
        val Factory = viewModelFactory {
            initializer {
                // APPLICATION_KEY es una clave especial que se utiliza para obtener la aplicación
                val application = this[APPLICATION_KEY] as MovieApplication
                // MovieViewModel(application.movieRepository) crea una instancia de MovieViewModel
                // con una instancia de MovieRepository proporcionada por MovieApplication
                MovieViewModel(application.movieRepository)
            }
        }
    }

}