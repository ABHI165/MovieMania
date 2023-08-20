package com.abhi165.moviemania.android.common

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val name: String
    val route: String
    val routeWithArgument: String
}

object Home : Destination {
    override val name: String
        get() = "Popular Movies"
    override val route: String
        get() = "home"
    override val routeWithArgument: String
        get() = route
}

object Detail : Destination {
    override val name: String
        get() = "Movie Detail"
    override val route: String
        get() = "detail"
    override val routeWithArgument: String
        get() = "$route/{movieID}"
    val argument = listOf(navArgument("movieID") { type = NavType.IntType })

}

val movieDestination = listOf(Home, Detail)