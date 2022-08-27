package com.borshevskiy.currencyexchangetestapp.domain

import javax.inject.Inject

class SaveAndRemoveFromFavoritesUseCase @Inject constructor(private val repository: CurrencyRepository) {

    suspend operator fun invoke(currency: Currency) = repository.saveAndRemoveFromFavorites(currency)
}