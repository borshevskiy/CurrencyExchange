package com.borshevskiy.currencyexchangetestapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyDbModel::class, FavoriteCurrencyDbModel::class],version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao
}