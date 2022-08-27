package com.borshevskiy.currencyexchangetestapp.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.borshevskiy.currencyexchangetestapp.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    state: SavedStateHandle,
    readAndFilterCurrenciesUseCase: ReadAndFilterCurrenciesUseCase,
    readAndFilterFavCurrenciesUseCase: ReadAndFilterFavCurrenciesUseCase,
    private val getAllCurrenciesListUseCase: GetAllCurrenciesListUseCase,
    private val getFavoriteCurrenciesListUseCase: GetFavoriteCurrenciesListUseCase,
    private val saveAndRemoveFromFavoritesUseCase: SaveAndRemoveFromFavoritesUseCase
) : ViewModel() {

    private val filter = state["popularFilter"] ?: ""
    private val favFilter = state["favoritesFilter"] ?: ""

//    /** ROOM DATABASE **/
//    val readCurrencies = readAndFilterCurrenciesUseCase(filter).asLiveData()
//    val readFavoriteCurrencies = readAndFilterFavCurrenciesUseCase(favFilter).asLiveData()

    /** ROOM DATABASE **/
    val readCurrencies = readAndFilterCurrenciesUseCase(filter)
    val readFavoriteCurrencies = readAndFilterFavCurrenciesUseCase(favFilter)

    fun insertOrRemoveFromFavorites(currency: Currency) = viewModelScope.launch {
        saveAndRemoveFromFavoritesUseCase(currency)
    }

    /** RETROFIT **/
    fun getCurrencies(query: String) = viewModelScope.launch {
        getAllCurrenciesListUseCase(query)
    }

    fun getFavoriteCurrencies(query: String, list: String) = viewModelScope.launch {
        getFavoriteCurrenciesListUseCase(query, list)
    }
}