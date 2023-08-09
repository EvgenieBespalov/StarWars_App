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
import com.example.starwars_app.domain.entity.StarshipEntity
import com.example.starwars_app.presentation.*
import com.example.starwars_app.screen.navigation.Routes
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchStarShipsScreen(
    navController: NavHostController,
    viewModel: SearchStarshipsScreenViewModel = koinViewModel()
){
    val state by viewModel.state.observeAsState(SearchStarshipsScreenUiState.Initial)

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
            var nameStarships by remember { mutableStateOf("") }

            OutlinedTextField(
                modifier = Modifier
                    .padding(5.dp, 10.dp, 5.dp, 10.dp)
                    .height(55.dp)
                    .fillMaxWidth(),
                leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
                value = nameStarships,
                onValueChange = {
                    nameStarships = it
                    if (nameStarships.length > 1)
                        viewModel.searchStarships(nameStarships)
                },
                placeholder = {
                    Text(
                        "Enter the name of the starship",
                        fontSize = 20.sp
                    ) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Yellow,
                    placeholderColor = Color.White,
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor = Color.Yellow,
                    errorBorderColor = Color.Yellow,
                    containerColor = Color.Black,
                    focusedLeadingIconColor = Color.Yellow,
                    unfocusedLeadingIconColor = Color.Yellow,
                    disabledLeadingIconColor = Color.Yellow,
                    errorLeadingIconColor = Color.Yellow,
                ),
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(fontSize = 20.sp)
            )

            when(state){
                SearchStarshipsScreenUiState.Initial    -> Unit
                SearchStarshipsScreenUiState.Loading    -> ScreenLoadind()
                is SearchStarshipsScreenUiState.Content -> {
                    ListOfStarshipLazyVerticalGrid(navController = navController, starships = (state as SearchStarshipsScreenUiState.Content).starships.collectAsLazyPagingItems())
                }
                is SearchStarshipsScreenUiState.Error   -> ScreenError(errorText = (state as SearchStarshipsScreenUiState.Error).message.orEmpty())
            }
        }
    }
}

@Composable
fun ListOfStarshipLazyVerticalGrid(
    navController: NavHostController,
    starships: LazyPagingItems<StarshipEntity>
){
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        items(count = starships.itemCount) { index ->
            starships[index]?.let { StarshipBox(navController, it) }
        }
    }
}

@Composable
fun StarshipBox(
    navController: NavHostController,
    starship: StarshipEntity
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
                navController.navigate(Routes.InfoStarshipScreenRoute.route + "/${starship.id}")
            },
        contentAlignment = Alignment.BottomCenter,
    ){
        AsyncImage(
            modifier = Modifier
                .size(200.dp),
            model = starship.image,
            error = painterResource(R.drawable.placeholder_image),
            contentScale = ContentScale.Crop,
            contentDescription = "Icon starship"
        )
        Text(
            modifier = Modifier
                .padding(5.dp),
            text = starship.name,
            color = Color.Yellow,
            fontSize = 20.sp
        )
    }
}