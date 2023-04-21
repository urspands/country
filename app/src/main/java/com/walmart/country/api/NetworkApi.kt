package com.walmart.country.api

import com.walmart.country.api.pojo.CountryResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * An interface which defines the network apis to fetch data from the server
 */
interface NetworkApi {
    companion object {
        private const val BASE_URL = "https://gist.githubusercontent.com/"
        const val COUNTRY_PATH =
            "peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        /**
         * Provides a instance of the [NetworkApi] to make api calls to server
         */
        fun getNetworkApi(): NetworkApi =
            retrofit.create(NetworkApi::class.java)

    }

    /**
     * gets the list of countries from the server
     */
    @GET(COUNTRY_PATH)
    suspend fun fetchCountriesFromServer(): CountryResponse
}