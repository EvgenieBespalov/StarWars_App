package com.example.starwars_app.screen.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.starwars_app.screen.PlanetInfoScreen
import com.example.starwars_app.screen.ResourcesScreen
import com.example.starwars_app.screen.SearchCharactersScreen
import com.example.starwars_app.screen.SearchPlanetsScreen

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

            composable(Routes.PlanetInfoScreenRoute.route) {
                PlanetInfoScreen()
            }
        }
    )
}