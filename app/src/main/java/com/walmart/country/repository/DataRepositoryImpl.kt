package com.walmart.country.repository

import com.walmart.country.api.NetworkApi
import com.walmart.country.api.pojo.CountryResponse

class DataRepositoryImpl(private val networkApi: NetworkApi) : DataRepository {
    override suspend fun getCountries(): DataResult<CountryResponse> {
        return try {
            val response = networkApi.fetchCountriesFromServer()
            DataResult.Success(response)
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }
}