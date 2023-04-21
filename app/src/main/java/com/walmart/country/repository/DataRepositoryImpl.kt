package com.walmart.country.repository

import com.walmart.country.api.NetworkApi
import com.walmart.country.api.pojo.CountryResponse

/**
 * A repository class which exposes apis to load data from network
 */
class DataRepositoryImpl(private val networkApi: NetworkApi) : DataRepository {
    /**
     * Fetches the countries list from the server
     * @return [DataResult<CountryResponse>]
     */
    override suspend fun getCountries(): DataResult<CountryResponse> {
        return try {
            val response = networkApi.fetchCountriesFromServer()
            DataResult.Success(response)
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }
}