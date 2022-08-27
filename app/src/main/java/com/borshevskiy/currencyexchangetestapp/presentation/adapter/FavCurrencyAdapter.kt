package com.borshevskiy.currencyexchangetestapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.borshevskiy.currencyexchangetestapp.R
import com.borshevskiy.currencyexchangetestapp.databinding.CurrencyItemBinding
import com.borshevskiy.currencyexchangetestapp.domain.Currency
import com.borshevskiy.currencyexchangetestapp.presentation.MainViewModel
import javax.inject.Inject

class FavCurrencyAdapter @Inject constructor(private val mainViewModel: MainViewModel): ListAdapter<Currency, FavCurrencyAdapter.CurrencyViewHolder>(CurrencyDiffCallback) {

    class CurrencyViewHolder(val binding: CurrencyItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CurrencyViewHolder(CurrencyItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val favCurrency = getItem(position)
        with(holder.binding) {
            currencyName.text = favCurrency.name
            currencyValue.text = favCurrency.value
            isFavorite.setBackgroundResource(R.drawable.ic_star_clicked)
            isFavorite.setOnClickListener {
                mainViewModel.insertOrRemoveFromFavorites(Currency(favCurrency.name, favCurrency.value, true))
            }
        }
    }
}