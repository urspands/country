package com.walmart.country.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.walmart.country.R
import com.walmart.country.databinding.ListItemBinding
import com.walmart.country.viewModel.CountryUiModel

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

    fun addCountries(countries: List<CountryUiModel>) {
        _countryUiModels.addAll(countries)
        notifyDataSetChanged()
    }
}