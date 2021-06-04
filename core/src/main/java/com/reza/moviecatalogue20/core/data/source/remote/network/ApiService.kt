package com.reza.movieapi.helper


import com.reza.moviecatalogue20.core.data.source.remote.response.MovieResponses
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getPopularMovies(@Query("api_key") apikey: String): MovieResponses




}