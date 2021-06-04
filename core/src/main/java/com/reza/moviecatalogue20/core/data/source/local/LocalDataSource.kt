package com.reza.moviecatalogue20.core.data.source.local

import com.reza.moviecatalogue20.core.data.source.local.entity.MovieEntity
import com.reza.moviecatalogue20.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mMovieDao: MovieDao){

    suspend fun insertMovieLocal(movie: List<MovieEntity>) = mMovieDao.insertMovieRoom(movie)
    fun getAllMovieLocal() : Flow<List<MovieEntity>> = mMovieDao.getAllMovieRoom()
    fun getFavMoviesLocal(): Flow<List<MovieEntity>> = mMovieDao.getFavMoviesRoom()
    fun setFavMovieLocal(movie: MovieEntity, newState:Boolean){
        movie.favourite = newState
        mMovieDao.setFavMovieRoom(movie)
    }




}