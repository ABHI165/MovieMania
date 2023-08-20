package com.abhi165.moviemania.data.remote.DTO

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDTO(
    val id: Int?,
    val overview: String?,
    val title: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("poster_path")
    val posterImage: String?
)
