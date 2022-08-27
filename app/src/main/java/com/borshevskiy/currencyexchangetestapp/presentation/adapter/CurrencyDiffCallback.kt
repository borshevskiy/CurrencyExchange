package com.borshevskiy.currencyexchangetestapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.borshevskiy.currencyexchangetestapp.domain.Currency

object CurrencyDiffCallback: DiffUtil.ItemCallback<Currency>() {

    override fun areItemsTheSame(oldItem: Currency, newItem: Currency) = oldItem.isFavorite == newItem.isFavorite

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency) = oldItem == newItem
}