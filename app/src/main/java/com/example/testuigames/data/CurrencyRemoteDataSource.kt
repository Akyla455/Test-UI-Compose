package com.example.testuigames.data

import com.example.testuigames.network.CurrencyRetrofit
import com.example.testuigames.network.CurrencyService

class CurrencyRemoteDataSource(
    private val currencyRetrofit: CurrencyRetrofit
) {
  val currencyService: CurrencyService by lazy {
      currencyRetrofit.retrofit.create(CurrencyService::class.java)
  }
}