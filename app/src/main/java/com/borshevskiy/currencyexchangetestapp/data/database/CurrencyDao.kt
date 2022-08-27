package com.borshevskiy.currencyexchangetestapp.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencyList: List<CurrencyDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCurrencies(currencyList: List<FavoriteCurrencyDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCurrency(favoriteCurrency: FavoriteCurrencyDbModel)

    @Update
    suspend fun updateCurrency(currency: CurrencyDbModel)

    @Query("UPDATE all_currencies_list SET isFavorite=:isFavorite WHERE name=:name")
    suspend fun backupFavorites(name: String, isFavorite: Boolean)

    @Query("SELECT * FROM all_currencies_list ORDER BY name ASC")
    fun readCurrenciesNameAsc(): Flow<List<CurrencyDbModel>>

    @Query("SELECT * FROM all_currencies_list ORDER BY name DESC")
    fun readCurrenciesNameDesc(): Flow<List<CurrencyDbModel>>

    @Query("SELECT * FROM all_currencies_list ORDER BY value ASC")
    fun readCurrenciesValueAsc(): Flow<List<CurrencyDbModel>>

    @Query("SELECT * FROM all_currencies_list ORDER BY value DESC")
    fun readCurrenciesValueDesc(): Flow<List<CurrencyDbModel>>

    @Query("SELECT * FROM favorite_currencies_list ORDER BY name ASC")
    fun readFavoriteCurrenciesNameAsc(): Flow<List<FavoriteCurrencyDbModel>>

    @Query("SELECT * FROM favorite_currencies_list ORDER BY name DESC")
    fun readFavoriteCurrenciesNameDesc(): Flow<List<FavoriteCurrencyDbModel>>

    @Query("SELECT * FROM favorite_currencies_list ORDER BY value ASC")
    fun readFavoriteCurrenciesValueAsc(): Flow<List<FavoriteCurrencyDbModel>>

    @Query("SELECT * FROM favorite_currencies_list ORDER BY value DESC")
    fun readFavoriteCurrenciesValueDesc(): Flow<List<FavoriteCurrencyDbModel>>

    @Delete
    suspend fun deleteFavoriteCurrency(favoriteCurrency: FavoriteCurrencyDbModel)
}