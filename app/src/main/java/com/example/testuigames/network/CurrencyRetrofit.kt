package com.example.testuigames.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyRetrofit {
    private val BASE_URL = "https://openexchangerates.org/api/"
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}