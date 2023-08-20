package com.abhi165.moviemania.domain.usecase

import com.abhi165.moviemania.domain.model.Movie
import com.abhi165.moviemania.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetPopularMovieUseCase: KoinComponent {
    private val repository: MovieRepository by inject()
    @Throws(Exception::class)
    suspend operator fun invoke(page: Int): List<Movie>{
        return repository.getPopularMovies(page)
    }
}