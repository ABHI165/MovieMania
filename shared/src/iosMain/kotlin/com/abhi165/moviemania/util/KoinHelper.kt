package com.abhi165.moviemania.util

import com.abhi165.moviemania.di.getSharedModule
import org.koin.core.context.startKoin


fun initKoin() {
    startKoin {
        modules(getSharedModule())
    }
}


