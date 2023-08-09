package com.example.starwars_app.screen.navigation

import android.service.controls.ControlsProviderService
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.starwars_app.screen.*
import retrofit2.HttpException

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Routes.ResourcesScreenRoute.route,
        modifier = Modifier.padding(paddingValues = padding),
        builder = {

            composable(Routes.ResourcesScreenRoute.route) {
                ResourcesScreen(navController = navController)
            }

            composable(Routes.SearchPlanetsScreenRoute.route) {
                SearchPlanetsScreen(navController = navController)
            }

            composable(Routes.SearchCharactersScreenRoute.route) {
                SearchCharactersScreen(navController = navController)
            }

            composable(Routes.SearchStarShipsScreenRoute.route) {
                SearchStarShipsScreen(navController = navController)
            }

            composable(Routes.InfoPlanetScreenRoute.route) {
                InfoPlanetScreen()
            }

            composable(Routes.InfoCharacterScreenRoute.route) {
                InfoCharacterScreen()
            }

            composable(Routes.InfoStarShipScreenRoute.route) {
                InfoStarShipScreen()
            }
        }
    )
}