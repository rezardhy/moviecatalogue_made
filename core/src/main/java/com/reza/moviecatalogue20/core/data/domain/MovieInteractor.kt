package com.reza.moviecatalogue20.core.data.domain


import com.reza.moviecatalogue20.core.data.source.Resource

import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getPopularMovies()
    }


    override fun setFavouriteMovie(movie: Movie, newState: Boolean) {
        return movieRepository.setFavouriteMovie(movie,newState)
    }

    override fun getFavouriteMovies(): Flow<List<Movie>> {
        return movieRepository.getFavouriteMovie()
    }


}