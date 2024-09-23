package com.example.guitarapp.ui.guitar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guitarapp.data.GuitarsRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class GuitarEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val guitarsRepository: GuitarsRepository
) : ViewModel() {
    var guitarUiState by mutableStateOf(GuitarUiState())
        private set

    private val guitarId: Int = checkNotNull(savedStateHandle[GuitarEditDestination.guitarIdArg])

    init {
        viewModelScope.launch{
            guitarUiState = guitarsRepository.getGuitarStream(guitarId)
                .filterNotNull()
                .first()
                .toGuitarUiState(true)
        }
    }


    private fun validateInput(uiState: GuitarDetails = guitarUiState.guitarDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }

    fun updateUiState(guitarDetails: GuitarDetails) {
        guitarUiState =
            GuitarUiState(guitarDetails = guitarDetails, isEntryValid = validateInput(guitarDetails))
    }

    suspend fun updateGuitar() {
        if (validateInput(guitarUiState.guitarDetails)) {
            guitarsRepository.updateGuitar(guitarUiState.guitarDetails.toGuitar())
        }
    }
}
