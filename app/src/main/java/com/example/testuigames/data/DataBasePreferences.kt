package com.example.testuigames.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class DataBasePreferences {
    private val pref: SharedPreferences = Application()
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