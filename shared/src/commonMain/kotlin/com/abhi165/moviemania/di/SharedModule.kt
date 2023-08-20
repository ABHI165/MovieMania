package com.abhi165.moviemania.di

import com.abhi165.moviemania.data.remote.MovieService
import com.abhi165.moviemania.data.remote.RemoteDataSource
import com.abhi165.moviemania.data.remote.client
import com.abhi165.moviemania.data.repository.MovieRepositoryImpl
import com.abhi165.moviemania.domain.repository.MovieRepository
import com.abhi165.moviemania.domain.usecase.GetMovieDetailUseCase
import com.abhi165.moviemania.domain.usecase.GetPopularMovieUseCase
import com.abhi165.moviemania.util.provideDispatcher
import org.koin.dsl.module


private val dataModule = module {
    single {
        MovieService(client = client)
    }



    single {
        RemoteDataSource(get(), get())
    }

    factory {
        provideDispatcher()
    }
}

private val domainModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }


    factory {
        GetMovieDetailUseCase()
    }


    factory {
        GetPopularMovieUseCase()
    }
}

private val sharedModule = listOf(domainModule, dataModule)

fun getSharedModule() = sharedModule