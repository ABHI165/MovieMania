package com.abhi165.moviemania.android.di

import com.abhi165.moviemania.android.detail.DetailViewModel
import com.abhi165.moviemania.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel {parameter->
        DetailViewModel(get(), parameter.get())
    }
}