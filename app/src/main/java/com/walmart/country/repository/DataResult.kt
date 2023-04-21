package com.walmart.country.repository

/**
 * A sealed class which is used to return data from repository to viewModel
 */
sealed class DataResult<out D> {
    data class Success<S>(val data: S) : DataResult<S>()
    data class Error(val exception: Exception) : DataResult<Nothing>()
}

