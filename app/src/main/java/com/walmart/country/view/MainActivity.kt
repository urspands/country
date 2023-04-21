package com.walmart.country.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.walmart.country.ViewModelFactory
import com.walmart.country.adapters.CountryAdapter
import com.walmart.country.api.NetworkApi
import com.walmart.country.databinding.ActivityMainBinding
import com.walmart.country.repository.DataRepositoryImpl
import com.walmart.country.viewModel.MainViewModel
import com.walmart.country.viewModel.UiState

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var _countryAdapter: CountryAdapter
    private val _dataRepository = DataRepositoryImpl(NetworkApi.getNetworkApi())
    private val _viewModel: MainViewModel by viewModels {
        ViewModelFactory(
            application,
            _dataRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _countryAdapter = CountryAdapter()
        _binding.countryRecyclerView.apply {
            adapter = _countryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(false)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        _viewModel.uiState.observe(this) {

            when (it) {
                is UiState.Error -> Toast.makeText(
                    baseContext,
                    it.exception.toString(),
                    Toast.LENGTH_SHORT
                ).show()

                UiState.Loading -> {

                }

                is UiState.Success -> {
                    _countryAdapter.addCountries(it.data)

                }
            }
        }
    }
}

