package com.abhi165.moviemania.data.remote.DTO

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val results: List<MovieDTO>
)
