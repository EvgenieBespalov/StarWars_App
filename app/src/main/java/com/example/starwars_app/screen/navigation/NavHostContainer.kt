package com.example.starwars_app.screen.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        modifier = Modifier.padding(paddingValues = padding),
        builder = {

            composable(Routes.ResourcesScreenRoute.route) {
                ResourcesScreen(navController = navController)
            }

            composable(Routes.SearchPlanetsScreenRoute.route) {
                SearchPlanetsScreen(navController = navController)
            }

            composable(Routes.SearchCharactersScreenRoute.route) {
                SearchPeoplesScreen(navController = navController)
            }

            composable(Routes.SearchStarShipsScreenRoute.route) {
                SearchStarShipsScreen(navController = navController)
            }

            composable(Routes.InfoPlanetScreenRoute.route + "/{planetId}") {
                InfoPlanetScreen(planetId = it.arguments?.getString("planetId"))
            }

            composable(Routes.InfoPeopleScreenRoute.route + "/{peopleId}") {
                InfoPeopleScreen(peopleId = it.arguments?.getString("peopleId"))
            }

            composable(Routes.InfoStarshipScreenRoute.route + "/{starshipId}") {
                InfoStarshipScreen(starshipId = it.arguments?.getString("starshipId"))
            }
        }
    )
}