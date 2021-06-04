package com.reza.moviecatalogue20.core.data.domain

import com.reza.moviecatalogue20.core.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun setFavouriteMovie(movie: Movie, newState:Boolean)
    fun getFavouriteMovie(): Flow<List<Movie>>

}