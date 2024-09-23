package com.example.guitarapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.guitarapp.GuitarApplication
import com.example.guitarapp.ui.home.HomeViewModel
import com.example.guitarapp.ui.guitar.GuitarDetailsViewModel
import com.example.guitarapp.ui.guitar.GuitarEditViewModel
import com.example.guitarapp.ui.guitar.GuitarEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            GuitarEditViewModel(
                this.createSavedStateHandle(),
                crudApplication().container.guitarsRepository
            )
        }

        initializer {
            GuitarEntryViewModel(crudApplication().container.guitarsRepository)
        }

        initializer {
            GuitarDetailsViewModel(
                this.createSavedStateHandle(),
                crudApplication().container.guitarsRepository
            )
        }

        initializer {
            HomeViewModel(crudApplication().container.guitarsRepository)
        }
    }
}

fun CreationExtras.crudApplication(): GuitarApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as GuitarApplication)