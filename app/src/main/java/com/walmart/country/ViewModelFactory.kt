package com.walmart.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.walmart.country.repository.DataRepository
import com.walmart.country.viewModel.MainViewModel

/**
 *  A ViewModelProvider.Factory class which provides a instance of the [MainViewModel]
 *  @param dataRepository [DataRepository]
 */
class ViewModelFactory(private val dataRepository: DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == MainViewModel::class.java) {
            MainViewModel(dataRepository) as T
        } else {
            super.create(modelClass)
        }
    }
}