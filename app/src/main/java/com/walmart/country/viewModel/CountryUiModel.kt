package com.walmart.country.viewModel

/**
 *  A simple data class which is used to pass the data to the CountryAdapter
 */
data class CountryUiModel(
    val name: String,
    val region: String,
    val code: String,
    val capital: String
)