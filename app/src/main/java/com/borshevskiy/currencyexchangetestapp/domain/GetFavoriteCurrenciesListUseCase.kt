package com.borshevskiy.currencyexchangetestapp.domain

import javax.inject.Inject

class GetFavoriteCurrenciesListUseCase @Inject constructor(private val repository: CurrencyRepository) {

    suspend operator fun invoke(query: String, list: String) = repository.getFavoriteCurrenciesList(query, list)

}