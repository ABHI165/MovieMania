package com.abhi165.moviemania.domain.model


data class Movie(
    val id: Int,
    val overview: String,
    val title: String,
    val releaseDate: String,
    val posterImage: String
)
