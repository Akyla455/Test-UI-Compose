package com.example.testuigames.network


import com.example.testuigames.model.DataCurrency
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface CurrencyApi {
    @GET("latest.json?app_id=686b5827047e4e33bdc06effd904e56d")
    fun getCurrencyData(): Call<DataCurrency>
}
object NetworkManager {
    private const val BASE_URL = "https://openexchangerates.org/api/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val currencyApi: CurrencyApi = retrofit.create(CurrencyApi::class.java)
}
