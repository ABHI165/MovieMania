package com.abhi165.moviemania.data.remote

import com.abhi165.moviemania.data.remote.DTO.MovieDetailResponse
import com.abhi165.moviemania.data.remote.DTO.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class MovieService(private val client: HttpClient) {

    suspend fun getPopularMovies(page: Int): MovieResponse = client.get("/3/movie/popular") {
        parameter("page", page)
    }.body()

    suspend fun getMoviesDetail(id: Int): MovieDetailResponse =
        com.abhi165.moviemania.data.remote.client.get("/3/movie/$id").body()
}