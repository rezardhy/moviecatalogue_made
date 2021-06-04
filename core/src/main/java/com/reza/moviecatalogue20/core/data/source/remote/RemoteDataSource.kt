package com.reza.moviecatalogue20.core.data.source.remote


import android.util.Log
import com.reza.movieapi.helper.ApiService
import com.reza.moviecatalogue20.core.data.source.remote.network.ApiResponse
import com.reza.moviecatalogue20.core.data.source.remote.response.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource(private val apiService: ApiService) {

    fun getPopularMoviesRemote():Flow<ApiResponse<List<ResultsItem>>> {

        return flow {
            try {
                val response = apiService.getPopularMovies(apikey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

    companion object {
        const val apikey = "0194abcf56cd26621b27865068010066"
    }




}