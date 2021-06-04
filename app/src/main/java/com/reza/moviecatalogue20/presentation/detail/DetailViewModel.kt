package com.reza.moviecatalogue20.presentation.detail

import androidx.lifecycle.ViewModel
import com.reza.moviecatalogue20.core.data.domain.Movie
import com.reza.moviecatalogue20.core.data.domain.MovieUseCase

class DetailViewModel(private val useCase: MovieUseCase)  : ViewModel() {
    fun setFavourite(movie: Movie, state:Boolean) =useCase.setFavouriteMovie(movie,state)
}