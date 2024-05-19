package com.example.testuigames.data

import android.content.Context
import android.content.SharedPreferences

class DataBasePreferences(
    context: Context
) {
    private val pref: SharedPreferences = context
        .getSharedPreferences("DataCurrency", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()
    fun saveData(value: Int){
        editor
            .putInt("currency", value)
            .apply()
    }
    fun deleteData(){
        editor
            .clear()
    }
    fun getData(): Int{
        return pref.getInt("currency", 10)
    }
}