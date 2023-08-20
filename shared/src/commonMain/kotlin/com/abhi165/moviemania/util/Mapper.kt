package com.abhi165.moviemania.util

import com.abhi165.moviemania.data.remote.DTO.MovieDTO
import com.abhi165.moviemania.data.remote.DTO.MovieDetailResponse
import com.abhi165.moviemania.domain.model.Movie


internal fun MovieDTO.toMovie() : Movie {
    return Movie(
        id = id ?: 0,
        overview = overview ?: "",
        title = title?: "",
        releaseDate = releaseDate ?: "",
        posterImage = getPosterImageURL(posterImage)
    )
}

internal fun MovieDetailResponse.toMovie() : Movie {
    return Movie(
        id = id ?: 0,
        overview = overview ?: "",
        title = title?: "",
        releaseDate = releaseDate ?: "",
        posterImage = getPosterImageURL(posterPath)
    )
}


private fun getPosterImageURL(url: String?) = "https://image.tmdb.org/t/p/w500/$url"