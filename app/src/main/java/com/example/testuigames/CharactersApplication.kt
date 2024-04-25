package com.example.testuigames

import android.app.Application
import com.example.testuigames.data.CharacterRemoteDataSource
import com.example.testuigames.data.CharactersRepository
import com.example.testuigames.data.NetworkCharactersRepository
import com.example.testuigames.network.CharacterRetrofit
import com.example.testuigames.viewModels.CharactersViewModel
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
                rootModule()
            )

            androidContext(this@CharactersApplication)
        }
    }

    private fun rootModule() = module {
        single { CharacterRetrofit() }
        single { CharacterRemoteDataSource(get()) }
        single<CharactersRepository> { NetworkCharactersRepository(get()) }
        viewModel { CharactersViewModel(get()) }
    }
}