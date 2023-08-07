package com.example.starwars_app.screen

import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.starwars_app.screen.navigation.NavHostContainer
import com.example.starwars_app.screen.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomMenuScreen(){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        },
        content = { padding ->
            NavHostContainer(navController = navController, padding = padding)
        }
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController){
    NavigationBar(
        modifier = Modifier
            .border(width = 1.dp, color = Color.Black),
        containerColor = Color.Black,
        //contentColor = Color.Red
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route


        ListOfBottomMenuItems.BottomMenuItems.forEach { menuItem ->
            NavigationBarItem(
                selected = (currentRoute == menuItem.route),
                onClick = {
                    //navController.navigate(menuItem.route)
                },
                icon = {
                    Icon(
                        imageVector = menuItem.icon,
                        contentDescription = menuItem.label
                    )
                },
                label = {
                    Text(
                        text = menuItem.label
                    )
                },
                enabled = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Yellow,
                    selectedTextColor = Color.Yellow,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                )
                //alwaysShowLabel = false
            )
        }
    }
}

data class BottomMenuItem(val label: String, val icon: ImageVector, var route: String)

object ListOfBottomMenuItems {
    val BottomMenuItems = listOf(
        BottomMenuItem(
            label = "Search",
            icon = Icons.Outlined.Search,
            route = Routes.ResourcesScreenRoute.route
        ),
        BottomMenuItem(
            label = "Favorites",
            icon = Icons.Outlined.Star,
            route = Routes.FavouritesScreenRoute.route
        )
    )
}

@Preview
@Composable
fun BottomMenuScreenPrewiew(){


    BottomMenuScreen()
}