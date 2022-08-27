package com.borshevskiy.currencyexchangetestapp.di

import com.borshevskiy.currencyexchangetestapp.data.repository.CurrencyRepositoryImpl
import com.borshevskiy.currencyexchangetestapp.domain.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CurrencyRepositoryModule {

    @Binds
    abstract fun bindRepository(impl: CurrencyRepositoryImpl): CurrencyRepository
}