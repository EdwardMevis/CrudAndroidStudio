package com.example.guitarapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.guitarapp.ui.home.HomeDestination
import com.example.guitarapp.ui.home.HomeScreen
import com.example.guitarapp.ui.guitar.GuitarDetailsDestination
import com.example.guitarapp.ui.guitar.GuitarDetailsScreen
import com.example.guitarapp.ui.guitar.GuitarEditDestination
import com.example.guitarapp.ui.guitar.GuitarEditScreen
import com.example.guitarapp.ui.guitar.GuitarEntryDestination
import com.example.guitarapp.ui.guitar.GuitarEntryScreen

@Composable
fun GuitarAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToGuitarEntry = { navController.navigate(GuitarEntryDestination.route) },
                navigateToGuitarUpdate = {
                    navController.navigate("${GuitarDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = GuitarEntryDestination.route) {
            GuitarEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = GuitarDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(GuitarDetailsDestination.guitarIdArg) {
                type = NavType.IntType
            })
        ) {
            GuitarDetailsScreen(
                navigateToEditGuitar = { navController.navigate("${GuitarEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = GuitarEditDestination.routeWithArgs,
            arguments = listOf(navArgument(GuitarEditDestination.guitarIdArg) {
                type = NavType.IntType
            })
        ) {
            GuitarEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}