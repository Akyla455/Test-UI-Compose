package com.example.testuigames.data

import com.example.testuigames.model.DataCurrency
import retrofit2.Call

interface CurrencyRepository {
    fun getCurrency() : Call<DataCurrency>
}

class NetworkCurrencyRepository(
    private val currencyRemoteDataSource: CurrencyRemoteDataSource
): CurrencyRepository {
    override fun getCurrency(): Call<DataCurrency> {
        return currencyRemoteDataSource.currencyService.getCurrencyData(AppId.CURRENCY_APP_ID)
    }
}