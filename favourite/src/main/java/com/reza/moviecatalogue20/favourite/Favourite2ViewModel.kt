package com.reza.moviecatalogue20.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.reza.moviecatalogue20.core.data.domain.MovieUseCase

class Favourite2ViewModel(private val useCase: MovieUseCase)  : ViewModel() {

    val getDetailMovie = useCase.getFavouriteMovies().asLiveData()
}