package com.example.starwars_app.screen.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.starwars_app.screen.*

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Routes.ResourcesScreenRoute.route,
        //modifier = Modifier.padding(paddingValues = padding),
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
        }
    )
}