package com.example.guitarapp.ui.guitar

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guitarapp.R
import com.example.guitarapp.TopAppBar
import com.example.guitarapp.ui.AppViewModelProvider
import com.example.guitarapp.ui.navigation.Destination
import com.example.guitarapp.ui.theme.GuitarAppTheme
import kotlinx.coroutines.launch

object GuitarEditDestination : Destination {
    override val route = "guitar_edit"
    override val titleRes = R.string.edit_guitar_title
    const val guitarIdArg = "guitarId"
    val routeWithArgs = "$route/{$guitarIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuitarEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GuitarEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(GuitarEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        GuitarEntryBody(
            guitarUiState = viewModel.guitarUiState,
            onGuitarValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateGuitar()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GuitarEditScreenPreview() {
    GuitarAppTheme {
        GuitarEditScreen(navigateBack = { /*Do nothing*/ }, onNavigateUp = { /*Do nothing*/ })
    }
}
