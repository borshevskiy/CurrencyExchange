package com.borshevskiy.currencyexchangetestapp.data.network.model


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("last_updated_at")
    val lastUpdatedAt: String
)