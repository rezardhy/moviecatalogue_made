package com.reza.moviecatalogue20.presentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

import com.reza.moviecatalogue20.core.data.domain.MovieUseCase

class MainViewModel(private val useCase: MovieUseCase) : ViewModel() {
    val getPopularMovies = useCase.getPopularMovies().asLiveData()

}