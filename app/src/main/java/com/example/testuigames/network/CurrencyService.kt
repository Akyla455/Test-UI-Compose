package com.example.testuigames.network


import com.example.testuigames.model.DataCurrency
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface CurrencyService {
    @GET("latest.json?=")
    fun getCurrencyData(
        @Query("app_id") appId: String
    ): Call<DataCurrency>
}
