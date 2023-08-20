package com.abhi165.moviemania.domain.repository

import com.abhi165.moviemania.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): List<Movie>
    suspend fun getMovieDetail(id: Int): Movie
}