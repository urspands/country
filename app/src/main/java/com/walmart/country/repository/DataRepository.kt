package com.walmart.country.repository

import com.walmart.country.api.pojo.CountryResponse
/**
 * A data repository interface which will be implemented by the concrete repository implementing class
 */
interface DataRepository {
    suspend fun getCountries(): DataResult<CountryResponse>
}