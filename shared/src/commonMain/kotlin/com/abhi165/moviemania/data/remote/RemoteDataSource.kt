package com.abhi165.moviemania.data.remote

import com.abhi165.moviemania.util.Dispatcher
import kotlinx.coroutines.withContext

internal class RemoteDataSource(
    private val movieService: MovieService,
    private val dispatcher: Dispatcher
) {

    suspend fun getPopularMovie(page: Int) = withContext(dispatcher.io) {
        movieService.getPopularMovies(page)
    }

    suspend fun getMovieDetail(id: Int) = withContext(dispatcher.io) {
        movieService.getMoviesDetail(id)
    }
}