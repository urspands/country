package com.walmart.country.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.walmart.country.repository.DataRepository
import com.walmart.country.repository.DataResult

/**
 * A simple [ViewModel] subclass which loads and holds the data of list of countries
 * @param dataRepository [DataRepository] to make api calls
 */
class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {

    //the livedata which holds the ui state
    val uiState: LiveData<UiState<List<CountryUiModel>>> = loadCountryList()

    /**
     * loads the country list from the data repository and returns the data as [LiveData]
     */
    private fun loadCountryList(): LiveData<UiState<List<CountryUiModel>>> =
        liveData {
            //emitting loading state
            emit(UiState.Loading)
            when (val response = dataRepository.getCountries()) {
                is DataResult.Error -> {
                    //emitting error state
                    emit(UiState.Error(response.exception))
                }

                is DataResult.Success -> {
                    if (response.data.isEmpty()) {
                        //emitting empty state
                        emit(UiState.EmptyState)
                    } else {
                        //emitting success state
                        emit(UiState.Success(response.data.map {
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
        }


}