package com.abhi165.moviemania.android.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi165.moviemania.domain.model.Movie
import com.abhi165.moviemania.domain.usecase.GetMovieDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DetailScreenState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val movie: Movie? = null
)
class DetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val movieID: Int
): ViewModel() {
    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        getMovieDetail()
    }

    private fun getMovieDetail() {
        _uiState.value = _uiState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                val movie = getMovieDetailUseCase(movieID)
                _uiState.value = _uiState.value.copy(loading = false, movie = movie)
            }catch (error: Throwable) {
                _uiState.value = _uiState.value.copy(loading = false, errorMessage = error.localizedMessage)
            }
        }
    }
}