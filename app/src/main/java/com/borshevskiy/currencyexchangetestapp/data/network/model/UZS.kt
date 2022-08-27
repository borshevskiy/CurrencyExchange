package com.borshevskiy.currencyexchangetestapp.data.network.model


import com.google.gson.annotations.SerializedName

data class UZS(
    @SerializedName("code")
    val code: String,
    @SerializedName("value")
    val value: Double
)