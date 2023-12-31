package com.example.starwars_app.screen.navigation

sealed class Routes(val route: String) {
    object ResourcesScreenRoute : Routes("Resources")
    object ListOfFavoritesScreenRoute : Routes("ListOfFavorites")
    object SearchPlanetsScreenRoute : Routes("SearchPlanets")
    object SearchCharactersScreenRoute : Routes("SearchCharacters")
    object SearchStarShipsScreenRoute : Routes("SearchStarShip")
    object InfoPlanetScreenRoute : Routes("InfoPlanet")
    object InfoPeopleScreenRoute : Routes("InfoCharacter")
    object InfoStarshipScreenRoute : Routes("InfoStarShip")
}