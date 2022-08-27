package com.borshevskiy.currencyexchangetestapp.data.network.model


import com.google.gson.annotations.SerializedName

data class UAH(
    @SerializedName("code")
    val code: String,
    @SerializedName("value")
    val value: Double
)