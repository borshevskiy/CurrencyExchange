package com.borshevskiy.currencyexchangetestapp.data.network.model


import com.google.gson.annotations.SerializedName

data class UGX(
    @SerializedName("code")
    val code: String,
    @SerializedName("value")
    val value: Double
)