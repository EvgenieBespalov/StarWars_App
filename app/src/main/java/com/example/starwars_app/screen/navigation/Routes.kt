package com.example.starwars_app.screen.navigation

sealed class Routes(val route: String) {
    object ResourcesScreenRoute : Routes("Resources")
    object SearchPlanetsScreenRoute : Routes("SearchPlanets")
    object FavouritesScreenRoute : Routes("Favourites")
}