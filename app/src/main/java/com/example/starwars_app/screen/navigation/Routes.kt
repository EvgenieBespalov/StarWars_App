package com.example.starwars_app.screen.navigation

sealed class Routes(val route: String) {
    object ResourcesScreenRoute : Routes("Resources")
    object SearchPlanetsScreenRoute : Routes("SearchPlanets")
    object PlanetInfoScreenRoute : Routes("PlanetInfo")
    object FavouritesScreenRoute : Routes("Favourites")
}