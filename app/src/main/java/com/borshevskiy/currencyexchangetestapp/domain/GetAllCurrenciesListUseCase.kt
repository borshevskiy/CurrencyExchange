package com.borshevskiy.currencyexchangetestapp.domain

import javax.inject.Inject

class GetAllCurrenciesListUseCase @Inject constructor(private val repository: CurrencyRepository) {

    suspend operator fun invoke(query: String) = repository.getAllCurrenciesList(query)

}