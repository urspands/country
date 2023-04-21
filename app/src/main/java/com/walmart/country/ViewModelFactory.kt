package com.walmart.country

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.walmart.country.repository.DataRepository
import com.walmart.country.viewModel.MainViewModel

class ViewModelFactory(private val application: Application,private val dataRepository: DataRepository) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(dataRepository) as T
    }
}