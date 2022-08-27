package com.borshevskiy.currencyexchangetestapp.domain

import javax.inject.Inject

class ReadAndFilterCurrenciesUseCase @Inject constructor(private val repository: CurrencyRepository) {

    operator fun invoke(filter: String = "") = repository.readAndFilterCurrencies(filter)
}