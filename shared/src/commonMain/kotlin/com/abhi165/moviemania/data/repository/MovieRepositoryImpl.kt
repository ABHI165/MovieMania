package com.abhi165.moviemania.data.repository

import com.abhi165.moviemania.data.remote.RemoteDataSource
import com.abhi165.moviemania.domain.model.Movie
import com.abhi165.moviemania.domain.repository.MovieRepository
import com.abhi165.moviemania.util.toMovie

internal class MovieRepositoryImpl( private val remoteDataSource: RemoteDataSource): MovieRepository {

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        return remoteDataSource.getPopularMovie(page).results.map {
            it.toMovie()
        }
    }

    override suspend fun getMovieDetail(id: Int): Movie {
        return remoteDataSource.getMovieDetail(id).toMovie()
    }
}