package com.example.testuigames

import android.app.Application
import com.example.testuigames.data.CharacterRemoteDataSource
import com.example.testuigames.data.CharactersRepository
import com.example.testuigames.data.CurrencyRemoteDataSource
import com.example.testuigames.data.CurrencyRepository
import com.example.testuigames.data.DataBasePreferences
import com.example.testuigames.data.NetworkCharactersRepository
import com.example.testuigames.data.NetworkCurrencyRepository
import com.example.testuigames.network.CharacterRetrofit
import com.example.testuigames.network.CurrencyRetrofit
import com.example.testuigames.viewModels.CharactersViewModel
import com.example.testuigames.viewModels.GameViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class CharactersApplication: Application() {
    override fun onCreate() {
        initKoin()
        super.onCreate()
    }

    private fun initKoin() {
        startKoin {
            modules(
                characterRootModule(),
                gameRootModule()
            )
            androidContext(this@CharactersApplication)
        }
    }

    private fun characterRootModule() = module {
        single { CharacterRetrofit() }
        single { CharacterRemoteDataSource(get()) }
        single<CharactersRepository> { NetworkCharactersRepository(get()) }
        viewModel { CharactersViewModel(get()) }
    }
    private fun gameRootModule() = module {
        single { CurrencyRetrofit() }
        single { CurrencyRemoteDataSource(get()) }
        single<CurrencyRepository> { NetworkCurrencyRepository(get()) }
        single { DataBasePreferences(get()) }
        viewModel { GameViewModel(get(), get()) }
    }
}