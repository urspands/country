package com.walmart.country.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.walmart.country.R
import com.walmart.country.databinding.ListItemBinding
import com.walmart.country.viewModel.CountryUiModel

/**
 * A simple [RecyclerView.Adapter] subclass which displays the list of countries with name, region, code and its capital in the recyclerview
 * use the [CountryAdapter.addCountries] function to add the astronauts to this adapter
 */
class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    private val _countryUiModels = ArrayList<CountryUiModel>()

    class CountryViewHolder(private val listItemBinding: ListItemBinding) :
        ViewHolder(listItemBinding.root) {
        fun bind(countryUiModel: CountryUiModel) {
            listItemBinding.apply {
                name.text = root.context.getString(
                    R.string.name_region,
                    countryUiModel.name,
                    countryUiModel.region
                )
                code.text = countryUiModel.code
                capital.text = countryUiModel.capital
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = _countryUiModels.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(_countryUiModels[position])
    }

    /**
     * adds the countries to the adapter and notifies the adapter to refresh
     * @param countries list of [CountryUiModel]
     */
    fun addCountries(countries: List<CountryUiModel>) {
        _countryUiModels.addAll(countries)
        notifyDataSetChanged()
    }
}