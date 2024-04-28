package com.example.testuigames.network


import com.example.testuigames.model.DataCurrency
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
interface CurrencyApi {
    @GET("latest.json?=")
    fun getCurrencyData(
        @Query("app_id") appId: String
    ): Call<DataCurrency>
}
object NetworkManager {
    private const val BASE_URL = "https://openexchangerates.org/api/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val currencyApi: CurrencyApi = retrofit.create(CurrencyApi::class.java)
}
