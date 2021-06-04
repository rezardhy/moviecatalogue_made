package com.reza.moviecatalogue20.favourite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteModule = module {
    viewModel { Favourite2ViewModel(get()) }
}