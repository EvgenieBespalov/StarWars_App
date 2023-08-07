package com.example.starwars_app.screen.navigation

sealed class Routes(val route: String) {
    object SearchScreenRoute : Routes("Search")
    object FavouritesScreenRoute : Routes("Favourites")
}