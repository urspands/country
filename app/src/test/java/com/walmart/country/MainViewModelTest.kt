package com.walmart.country

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.walmart.country.api.pojo.CountryResponse
import com.walmart.country.api.pojo.CountryResponseItem
import com.walmart.country.api.pojo.Currency
import com.walmart.country.api.pojo.Language
import com.walmart.country.repository.DataRepository
import com.walmart.country.repository.DataResult
import com.walmart.country.viewModel.CountryUiModel
import com.walmart.country.viewModel.MainViewModel
import com.walmart.country.viewModel.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 *  A test class with unit tests for [MainViewModel]
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineTestRule()

    @Mock
    private lateinit var repository: DataRepository

    @Mock
    private lateinit var uiStateObserver: Observer<UiState<List<CountryUiModel>>>

    /**
     * Test for success response for the list of countries
     */
    @Test
    fun testSuccessResponseForListOfCountries() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(repository.getCountries()).thenReturn(
                DataResult.Success(getDummyCountries())
            )
        }
        val viewModel = MainViewModel(repository)
        viewModel.uiState.observeForever(uiStateObserver)
        runBlocking { Mockito.verify(repository).getCountries() }
        Mockito.verify(uiStateObserver)
            .onChanged(UiState.Success(getDummyCountries().map {
                CountryUiModel(
                    name = it.name,
                    region = it.region,
                    code = it.code,
                    capital = it.capital
                )
            }))
        viewModel.uiState.removeObserver(uiStateObserver)
    }
    /**
     * Test for success empty response for the list of countries
     */
    @Test
    fun testEmptyResponseForListOfCountries() {
        val emptyResponse = CountryResponse()
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(repository.getCountries()).thenReturn(
                DataResult.Success(emptyResponse)
            )
        }
        val viewModel = MainViewModel(repository)
        viewModel.uiState.observeForever(uiStateObserver)
        runBlocking { Mockito.verify(repository).getCountries() }
        Mockito.verify(uiStateObserver)
            .onChanged(UiState.EmptyState)
        viewModel.uiState.removeObserver(uiStateObserver)
    }

    /**
     * Test for error response for the list of countries
     */
    @Test
    fun testErrorResponseForListOfCountries() {
        val exception = Exception("Oops")
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(repository.getCountries()).thenReturn(
                DataResult.Error(exception)
            )
        }
        val viewModel = MainViewModel(repository)
        viewModel.uiState.observeForever(uiStateObserver)
        runBlocking { Mockito.verify(repository).getCountries() }
        Mockito.verify(uiStateObserver)
            .onChanged(UiState.Error(exception))
        viewModel.uiState.removeObserver(uiStateObserver)
    }




    private fun getDummyCountries(): CountryResponse {
        return CountryResponse().apply {
            add(
                CountryResponseItem(
                    capital = "Kabul",
                    code = "AF",
                    name = "Afghanistan",
                    region = "AS",
                    currency = Currency(code = "AFN", name = "Afghan afghani", symbol = "؋"),
                    flag = "https://restcountries.eu/data/afg.svg",
                    language = Language(
                        code = "ps",
                        name = "Pashto",
                        iso639_2 = "",
                        nativeName = ""
                    ), demonym = ""
                )
            )
            add(
                CountryResponseItem(
                    capital = "Kabul",
                    code = "AF",
                    name = "Afghanistan",
                    region = "AS",
                    currency = Currency(code = "AFN", name = "Afghan afghani", symbol = "؋"),
                    flag = "https://restcountries.eu/data/afg.svg",
                    language = Language(
                        code = "ps",
                        name = "Pashto",
                        iso639_2 = "",
                        nativeName = ""
                    ), demonym = ""
                )
            )
            add(
                CountryResponseItem(
                    capital = "Kabul",
                    code = "AF",
                    name = "Afghanistan",
                    region = "AS",
                    currency = Currency(code = "AFN", name = "Afghan afghani", symbol = "؋"),
                    flag = "https://restcountries.eu/data/afg.svg",
                    language = Language(
                        code = "ps",
                        name = "Pashto",
                        iso639_2 = "",
                        nativeName = ""
                    ), demonym = ""
                )
            )

        }
    }
}