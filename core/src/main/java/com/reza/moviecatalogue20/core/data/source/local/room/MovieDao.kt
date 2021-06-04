package com.reza.moviecatalogue20.core.data.source.local.room

import androidx.room.*
import com.reza.moviecatalogue20.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieRoom(movie: List<MovieEntity>)

    @Query("SELECT * FROM movie")
    fun getAllMovieRoom(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where favourite = 1")
    fun getFavMoviesRoom(): Flow<List<MovieEntity>>

    @Update
    fun setFavMovieRoom(movie: MovieEntity)


}