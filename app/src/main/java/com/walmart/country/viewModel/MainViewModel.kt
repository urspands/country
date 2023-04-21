package com.walmart.country.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.walmart.country.repository.DataRepository
import com.walmart.country.repository.DataResult

class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val uiState: LiveData<UiState<List<CountryUiModel>>> = loadCountryList()

    private fun loadCountryList(): LiveData<UiState<List<CountryUiModel>>> =
        liveData {
            emit(UiState.Loading)
            when (val response = dataRepository.getCountries()) {
                is DataResult.Error -> emit(UiState.Error(response.exception))
                is DataResult.Success -> emit(UiState.Success(response.data.map {
                    CountryUiModel(
                        name = it.name,
                        region = it.region,
                        code = it.code,
                        capital = it.capital
                    )
                }))
            }
        }


}