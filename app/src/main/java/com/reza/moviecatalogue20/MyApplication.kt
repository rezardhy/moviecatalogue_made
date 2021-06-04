package com.reza.moviecatalogue20

import android.app.Application
import com.reza.moviecatalogue20.di.useCaseModule
import com.reza.moviecatalogue20.di.viewModelModule
import com.reza.moviecatalogue20.core.data.di.databaseModule
import com.reza.moviecatalogue20.core.data.di.networkModule
import com.reza.moviecatalogue20.core.data.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}