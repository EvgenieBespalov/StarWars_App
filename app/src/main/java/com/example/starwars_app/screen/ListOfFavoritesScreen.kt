package com.example.starwars_app.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.starwars_app.R
import com.example.starwars_app.domain.entity.DatabaseEntity
import com.example.starwars_app.presentation.InfoStarshipScreenUiState
import com.example.starwars_app.presentation.InfoStarshipScreenViewModel
import com.example.starwars_app.presentation.SearchFavoritesScreenUiState
import com.example.starwars_app.presentation.SearchFavoritesScreenViewModel
import com.example.starwars_app.screen.navigation.Routes
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListOfFavoritesScreen(
    navController: NavHostController,
    viewModel: SearchFavoritesScreenViewModel = koinViewModel()
){
    val state by viewModel.state.observeAsState(SearchFavoritesScreenUiState.Initial)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background_image),
                contentScale = ContentScale.None
            ),
        contentAlignment = Alignment.TopCenter
    ){
        Column(modifier = Modifier
            .fillMaxSize()
        ){
            when(state){
                SearchFavoritesScreenUiState.Initial    -> viewModel.loadFavorites()
                SearchFavoritesScreenUiState.Loading    -> ScreenLoadind()
                is SearchFavoritesScreenUiState.Content -> {
                    ListOfFavoritesLazyVerticalGrid(navController = navController, favorites = (state as SearchFavoritesScreenUiState.Content).favorites)
                }
                is SearchFavoritesScreenUiState.Error   -> ScreenError(errorText = (state as SearchFavoritesScreenUiState.Error).message.orEmpty())
            }
        }
    }
}

@Composable
fun ListOfFavoritesLazyVerticalGrid(
    navController: NavHostController,
    favorites: List<DatabaseEntity>
){
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        items(count = favorites.size) { index ->
            favorites[index].let { FavoriteBox(navController, it) }
        }
    }
}

@Composable
fun FavoriteBox(
    navController: NavHostController,
    favorite: DatabaseEntity
){
    Box(
        modifier = Modifier
            .padding(5.dp)
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(30.dp)
            }
            .border(
                width = 2.dp,
                color = Color.Yellow,
                shape = RoundedCornerShape(30.dp)
            )
            .clickable {
                when(favorite.type){
                    "planet" -> navController.navigate(Routes.InfoPlanetScreenRoute.route + "/${favorite.id}")
                    "people" -> navController.navigate(Routes.InfoPeopleScreenRoute.route + "/${favorite.id}")
                    "starship" -> navController.navigate(Routes.InfoStarshipScreenRoute.route + "/${favorite.id}")
                }
                       },
        contentAlignment = Alignment.BottomCenter,
    ){
        AsyncImage(
            modifier = Modifier
                .size(200.dp),
            model = favorite.image,
            error = painterResource(R.drawable.placeholder_image),
            contentScale = ContentScale.Crop,
            contentDescription = "Icon favorite"
        )
        Text(
            modifier = Modifier
                .padding(5.dp),
            text = favorite.name,
            color = Color.Yellow,
            fontSize = 20.sp
        )
    }
}