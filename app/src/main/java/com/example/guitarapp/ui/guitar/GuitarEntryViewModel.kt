package com.example.guitarapp.ui.guitar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.guitarapp.data.Guitar
import com.example.guitarapp.data.GuitarsRepository
import java.text.NumberFormat

class GuitarEntryViewModel(private val guitarsRepository: GuitarsRepository) : ViewModel() {

    var guitarUiState by mutableStateOf(GuitarUiState())
        private set


    fun updateUiState(guitarDetails: GuitarDetails) {
        guitarUiState =
            GuitarUiState(guitarDetails = guitarDetails, isEntryValid = validateInput(guitarDetails))
    }

    private fun validateInput(uiState: GuitarDetails = guitarUiState.guitarDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }

    suspend fun saveGuitar() {
        if (validateInput()) {
            guitarsRepository.insertGuitar(guitarUiState.guitarDetails.toGuitar())
        }
    }
}

data class GuitarUiState(
    val guitarDetails: GuitarDetails = GuitarDetails(),
    val isEntryValid: Boolean = false
)

data class GuitarDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)


fun GuitarDetails.toGuitar(): Guitar = Guitar(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0
)

fun Guitar.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

fun Guitar.toGuitarUiState(isEntryValid: Boolean = false): GuitarUiState = GuitarUiState(
    guitarDetails = this.toGuitarDetails(),
    isEntryValid = isEntryValid
)

fun Guitar.toGuitarDetails(): GuitarDetails = GuitarDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)
