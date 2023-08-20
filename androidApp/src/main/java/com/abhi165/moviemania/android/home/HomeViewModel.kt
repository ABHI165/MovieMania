package com.abhi165.moviemania.android.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi165.moviemania.domain.model.Movie
import com.abhi165.moviemania.domain.usecase.GetPopularMovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeViewState (
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val movies: List<Movie> = listOf(),
    val  error: String? = null
)

class HomeViewModel(
    private val getPopularMovieUseCase: GetPopularMovieUseCase
): ViewModel() {
    private var _uiState = MutableStateFlow(HomeViewState())
    val uiState = _uiState.asStateFlow()
    private var currentPage = 1

    init {
        getPopularMovies(true)
    }

    fun getPopularMovies(forceReload: Boolean = false) {
        if (_uiState.value.isLoading) return
        _uiState.value = _uiState.value.copy(isLoading = !forceReload, isRefreshing = forceReload)
        currentPage =  if (forceReload) 1 else currentPage + 1

        viewModelScope.launch {
            _uiState.value = try {
                val movies = getPopularMovieUseCase(currentPage)
                _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    movies = if (forceReload) movies else uiState.value.movies + movies,
                    error = null
                )
            } catch (error: Throwable) {
                _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    error = "Something went wrong ${error.message} "
                )
            }

        }
    }
}