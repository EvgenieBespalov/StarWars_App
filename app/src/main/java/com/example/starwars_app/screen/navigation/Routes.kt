package com.example.starwars_app.screen.navigation

sealed class Routes(val route: String) {
    object ResourcesScreenRoute : Routes("Resources")
    object SearchPlanetsScreenRoute : Routes("SearchPlanets")
    object SearchCharactersScreenRoute : Routes("SearchCharacters")
    object SearchStarShipsScreenRoute : Routes("SearchStarShip")
    object InfoPlanetScreenRoute : Routes("InfoPlanet")
    object InfoPeopleScreenRoute : Routes("InfoCharacter")
    object InfoStarShipScreenRoute : Routes("InfoStarShip")
    object FavouritesScreenRoute : Routes("Favourites")
}