package com.example.starwars_app.screen.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.starwars_app.screen.ResourcesScreen

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Routes.SearchScreenRoute.route,
        //modifier = Modifier.padding(paddingValues = padding),
        builder = {

            composable(Routes.SearchScreenRoute.route) {
                ResourcesScreen(navController = navController)
            }

            composable(Routes.FavouritesScreenRoute.route) {
                //RentalOffersListScreen(navController = navController)
            }

        })

}