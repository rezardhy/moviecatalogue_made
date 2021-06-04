package com.reza.moviecatalogue20.core.data

import com.reza.moviecatalogue20.core.data.domain.IMovieRepository
import com.reza.moviecatalogue20.core.data.domain.Movie
import com.reza.moviecatalogue20.core.data.source.Resource
import com.reza.moviecatalogue20.core.data.source.local.LocalDataSource
import com.reza.moviecatalogue20.core.data.source.remote.RemoteDataSource
import com.reza.moviecatalogue20.core.data.source.remote.network.ApiResponse
import com.reza.moviecatalogue20.core.data.source.remote.response.ResultsItem
import com.reza.moviecatalogue20.core.data.utils.AppExecutors
import com.reza.moviecatalogue20.core.data.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors

): IMovieRepository {

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> {


        return object : NetworkBoundResource<List<Movie>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovieLocal().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                    data == null || data.isEmpty()

            override suspend  fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                    remoteDataSource.getPopularMoviesRemote()

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovieLocal(movieList)
            }
        }.asFlow()
    }


    override fun setFavouriteMovie(movie:Movie, newState: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavMovieLocal(movieEntity, newState) }

    }

    override fun getFavouriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavMoviesLocal().map { DataMapper.mapEntitiesToDomain(it) }

    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null
    }


}