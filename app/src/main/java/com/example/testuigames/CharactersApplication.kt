package com.example.testuigames

import android.app.Application
import com.example.testuigames.data.AppContainer
import com.example.testuigames.data.DefaultAppContainer

class CharactersApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}