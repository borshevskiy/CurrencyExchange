package com.borshevskiy.currencyexchangetestapp.data.network

import com.borshevskiy.currencyexchangetestapp.data.network.model.CurrencyInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v3/latest")
    suspend fun getCurrenciesInfo(
        @Query("apikey") apiKey: String = "zJSt2VlZSQstwb1QZGkG5gYmw9omp4YuNSKZtQOI",
        @Query("base_currency") baseCurrency: String = "USD"
    ): Response<CurrencyInfoDto>

    @GET("v3/latest")
    suspend fun getFavoriteCurrenciesInfo(
        @Query("apikey") apiKey: String = "zJSt2VlZSQstwb1QZGkG5gYmw9omp4YuNSKZtQOI",
        @Query("base_currency") baseCurrency: String = "USD",
        @Query("currencies") currencies: String = ""
    ): Response<CurrencyInfoDto>
}