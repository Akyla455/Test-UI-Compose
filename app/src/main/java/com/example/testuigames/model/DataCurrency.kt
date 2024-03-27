package com.example.testuigames.model

import com.google.gson.annotations.SerializedName

data class DataCurrency(
    @SerializedName("rates")
    val rates: Rates
)

data class Rates(
    @SerializedName("AFN")
    val afn: Double
)
