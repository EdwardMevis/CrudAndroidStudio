package com.example.guitarapp

import android.app.Application
import com.example.guitarapp.data.AppContainer
import com.example.guitarapp.data.AppDataContainer

class GuitarApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}