package com.borshevskiy.currencyexchangetestapp.data.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.borshevskiy.currencyexchangetestapp.data.database.CurrencyDao
import com.borshevskiy.currencyexchangetestapp.data.mapper.CurrencyMapper
import com.borshevskiy.currencyexchangetestapp.data.network.ApiService
import com.borshevskiy.currencyexchangetestapp.domain.Currency
import com.borshevskiy.currencyexchangetestapp.domain.CurrencyRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class CurrencyRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiService: ApiService,
    private val mapper: CurrencyMapper,
    private val dao: CurrencyDao
) : CurrencyRepository {

    private val preferences = context.getSharedPreferences("app_settings", MODE_PRIVATE)

    override suspend fun getAllCurrenciesList(query: String) {
        val response = apiService.getCurrenciesInfo(baseCurrency = query)
        if (response.isSuccessful) {
            dao.insertCurrencies(mapper.mapDtoToDbModel(response.body()!!))
            if (preferences.contains("FAVORITES")) {
                val favList = preferences.getString("FAVORITES", "")!!.removeSuffix(",").split(",")
                favList.forEach { dao.backupFavorites(it, true) }
            }
        }
    }

    override suspend fun getFavoriteCurrenciesList(query: String, list: String) {
        val response = apiService.getFavoriteCurrenciesInfo(baseCurrency = query, currencies = list)
        if (response.isSuccessful) {
            dao.insertFavoriteCurrencies(mapper.mapDtoToFavDbModel(response.body()!!))
        }
    }

    override suspend fun saveAndRemoveFromFavorites(currency: Currency) {
        dao.updateCurrency(mapper.mapCurrencyToDbModel(currency))
        if (!currency.isFavorite) {
            dao.insertFavoriteCurrency(mapper.mapCurrencyToFavDbModel(currency))
        } else {
            dao.deleteFavoriteCurrency(mapper.mapCurrencyToFavDbModel(currency))
        }
    }

    override fun readAndFilterCurrencies(filter: String): Flow<List<Currency>> {
        return when (filter) {
            "nameDesc" -> dao.readCurrenciesNameDesc().mapLatest { listOfDbModels -> listOfDbModels.map { mapper.mapDbModelToCurrency(it) } }
            "valueAsc" -> dao.readCurrenciesValueAsc().mapLatest { listOfDbModels -> listOfDbModels.map { mapper.mapDbModelToCurrency(it) } }
            "valueDesc" -> dao.readCurrenciesValueDesc().mapLatest { listOfDbModels -> listOfDbModels.map { mapper.mapDbModelToCurrency(it) } }
            else -> dao.readCurrenciesNameAsc().mapLatest { listOfDbModels -> listOfDbModels.map { mapper.mapDbModelToCurrency(it) } }
        }
    }
    override fun readAndFilterFavoriteCurrencies(filter: String): Flow<List<Currency>> {
        return when (filter) {
            "nameDesc" -> dao.readFavoriteCurrenciesNameDesc().mapLatest { listOfFavDbModels -> listOfFavDbModels.map { mapper.mapFavDbModelToCurrency(it) } }
            "valueAsc" -> dao.readFavoriteCurrenciesValueAsc().mapLatest { listOfFavDbModels -> listOfFavDbModels.map { mapper.mapFavDbModelToCurrency(it) } }
            "valueDesc" -> dao.readFavoriteCurrenciesValueDesc().mapLatest { listOfFavDbModels -> listOfFavDbModels.map { mapper.mapFavDbModelToCurrency(it) } }
            else -> dao.readFavoriteCurrenciesNameAsc().mapLatest { listOfFavDbModels -> listOfFavDbModels.map { mapper.mapFavDbModelToCurrency(it) } }
        }
    }
}