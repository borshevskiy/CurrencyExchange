package com.borshevskiy.currencyexchangetestapp.domain

import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun getAllCurrenciesList(query: String)

    suspend fun getFavoriteCurrenciesList(query: String, list: String = "")

    suspend fun saveAndRemoveFromFavorites(currency: Currency)

    fun readAndFilterCurrencies(filter: String): Flow<List<Currency>>

    fun readAndFilterFavoriteCurrencies(filter: String): Flow<List<Currency>>
}