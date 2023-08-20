package com.abhi165.moviemania.android.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abhi165.moviemania.android.common.Detail
import com.abhi165.moviemania.android.common.Home
import com.abhi165.moviemania.android.common.TopAppBar
import com.abhi165.moviemania.android.common.movieDestination
import com.abhi165.moviemania.android.detail.DetailScreen
import com.abhi165.moviemania.android.detail.DetailViewModel
import com.abhi165.moviemania.android.home.view.HomeScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf

@Composable
fun MovieApp() {
    val navigationController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val scaffoldState = rememberScaffoldState()
    val backStack by navigationController.currentBackStackEntryAsState()
    val currentDestination = movieDestination.find {
        it.route == backStack?.destination?.route || it.routeWithArgument == backStack?.destination?.route
    } ?: Home

    val statusColor =
        if (isSystemInDarkTheme()) MaterialTheme.colors.primaryVariant else Color.Transparent
    systemUiController.setSystemBarsColor(statusColor)

    SideEffect {
        systemUiController.setSystemBarsColor(statusColor)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                currentDestination = currentDestination,
                canNavigateBack = navigationController.previousBackStackEntry != null
            ) {
                navigationController.navigateUp()
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navigationController,
            startDestination = Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Home.routeWithArgument) {
                val viewModel: HomeViewModel = koinViewModel()
                val state by viewModel.uiState.collectAsStateWithLifecycle()

                HomeScreen(
                    uiState = state,
                    loadNextMovies = { isForceReload ->
                        viewModel.getPopularMovies(isForceReload)
                    },
                    navigateToDetail = {
                        navigationController.navigate("${Detail.route}/${it.id}")
                    })
            }

            composable(
                Detail.routeWithArgument,
                arguments = Detail.argument
            ) {
                val movieId = it.arguments?.getInt("movieID")
                val viewModel: DetailViewModel = koinViewModel(
                    parameters = {
                        parametersOf(movieId)
                    }
                )
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                DetailScreen(uiState =  state)
            }
        }
    }
}