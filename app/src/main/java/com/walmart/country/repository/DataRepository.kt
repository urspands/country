package com.walmart.country.repository

import com.walmart.country.api.pojo.CountryResponse

interface DataRepository {
    suspend fun getCountries(): DataResult<CountryResponse>
}