package com.borshevskiy.currencyexchangetestapp.data.network.model


import com.google.gson.annotations.SerializedName

data class JMD(
    @SerializedName("code")
    val code: String,
    @SerializedName("value")
    val value: Double
)