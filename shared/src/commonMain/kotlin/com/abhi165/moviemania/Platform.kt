package com.abhi165.moviemania

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform