package com.example.guitarapp.ui.guitar

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guitarapp.data.GuitarsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GuitarDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val guitarsRepository: GuitarsRepository
) : ViewModel() {

    private val guitarId: Int = checkNotNull(savedStateHandle[GuitarDetailsDestination.guitarIdArg])

    val uiState: StateFlow<GuitarDetailsUiState> =
        guitarsRepository.getGuitarStream(guitarId)
            .filterNotNull()
            .map {
                GuitarDetailsUiState(outOfStock = it.quantity <= 0, guitarDetails = it.toGuitarDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = GuitarDetailsUiState()
            )



    fun reduceQuantityByOne() {
        viewModelScope.launch {
            val currentGuitar = uiState.value.guitarDetails.toGuitar()
            if (currentGuitar.quantity > 0) {
                guitarsRepository.updateGuitar(currentGuitar.copy(quantity = currentGuitar.quantity - 1))
            }
        }
    }

    suspend fun deleteGuitar() {
        guitarsRepository.deleteGuitar(uiState.value.guitarDetails.toGuitar())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class GuitarDetailsUiState(
    val outOfStock: Boolean = true,
    val guitarDetails: GuitarDetails = GuitarDetails()
)