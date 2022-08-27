package com.borshevskiy.currencyexchangetestapp.domain

import javax.inject.Inject

class ReadAndFilterFavCurrenciesUseCase @Inject constructor(private val repository: CurrencyRepository) {

    operator fun invoke(filter: String = "") = repository.readAndFilterFavoriteCurrencies(filter)
}