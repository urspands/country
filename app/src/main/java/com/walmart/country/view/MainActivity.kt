package com.walmart.country.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.walmart.country.ViewModelFactory
import com.walmart.country.api.NetworkApi
import com.walmart.country.databinding.ActivityMainBinding
import com.walmart.country.repository.DataRepositoryImpl
import com.walmart.country.view.adapters.CountryAdapter
import com.walmart.country.viewModel.CountryUiModel
import com.walmart.country.viewModel.MainViewModel
import com.walmart.country.viewModel.UiState

/**
 * The starting activity of this application which displays the list of countries
 */
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var _countryAdapter: CountryAdapter
    private val _dataRepository = DataRepositoryImpl(NetworkApi.getNetworkApi())
    private val _viewModel: MainViewModel by viewModels {
        ViewModelFactory(
            _dataRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        //setting up the UI components
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
        //observe data changes and update them in the UI
        _viewModel.uiState.observe(this) {
            bindDataToUi(it)
        }
    }

    /**
     * binds the data to the UI
     * @param uiState ui state from the view model
     */
    private fun bindDataToUi(uiState: UiState<List<CountryUiModel>>) {
        when (uiState) {
            is UiState.Error -> {
                showProgressBar(false)
                Toast.makeText(
                    baseContext,
                    uiState.exception.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }

            UiState.Loading -> {
                showProgressBar(true)
            }

            is UiState.Success -> {
                showProgressBar(false)
                _countryAdapter.addCountries(uiState.data)

            }
        }
    }

    /**
     * shows or hides the progress bar in the UI while loading the data
     * @param show true to show or false to hide
     */
    private fun showProgressBar(show: Boolean) {
        _binding.progressCircular.visibility = if (show) View.VISIBLE else View.GONE
    }
}

