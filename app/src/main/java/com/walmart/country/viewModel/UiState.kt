package com.walmart.country.viewModel

/**
 * A sealed class which is used to hold and update the UI state of the view
 */
sealed class UiState<out u> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()
}