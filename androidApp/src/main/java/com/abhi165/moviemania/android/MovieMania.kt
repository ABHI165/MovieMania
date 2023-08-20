package com.abhi165.moviemania.android

import android.app.Application
import com.abhi165.moviemania.android.di.appModule
import com.abhi165.moviemania.di.getSharedModule
import org.koin.core.context.startKoin

class MovieMania: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
             modules(appModule + getSharedModule() )
        }
    }
}