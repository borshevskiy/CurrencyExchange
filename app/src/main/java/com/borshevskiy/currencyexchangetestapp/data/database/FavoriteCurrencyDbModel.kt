package com.borshevskiy.currencyexchangetestapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_currencies_list")
data class FavoriteCurrencyDbModel(
    @PrimaryKey
    val name: String,
    val value: Double
)