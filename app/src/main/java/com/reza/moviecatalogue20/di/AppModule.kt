package com.reza.moviecatalogue20.di

import com.reza.moviecatalogue20.core.data.domain.MovieInteractor
import com.reza.moviecatalogue20.core.data.domain.MovieUseCase
import com.reza.moviecatalogue20.presentation.detail.DetailViewModel
import com.reza.moviecatalogue20.presentation.home.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}