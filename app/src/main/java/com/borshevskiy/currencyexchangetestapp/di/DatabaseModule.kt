package com.borshevskiy.currencyexchangetestapp.di

import android.content.Context
import androidx.room.Room
import com.borshevskiy.currencyexchangetestapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, "main.db"
    ).build()

    @Singleton
    @Provides
    fun provideDAO(database: AppDatabase) = database.currencyDao()
}