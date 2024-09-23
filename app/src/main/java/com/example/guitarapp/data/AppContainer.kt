package com.example.guitarapp.data

import android.content.Context

interface AppContainer {
    val guitarsRepository: GuitarsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val guitarsRepository: GuitarsRepository by lazy {
        OfflineGuitarsRepository(DBase.getDatabase(context).GuitarDao())
    }
}