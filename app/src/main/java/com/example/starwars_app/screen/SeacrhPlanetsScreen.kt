package com.example.starwars_app.screen

import android.service.controls.ControlsProviderService
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.starwars_app.R
import com.example.starwars_app.presentation.SearchPlanetsScreenUiState
import com.example.starwars_app.presentation.SearchPlanetsScreenViewModel
import com.example.starwars_app.screen.navigation.Routes
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.starwars_app.domain.entity.PlanetEntity

@Composable
fun SearchPlanetsScreen(
    navController: NavHostController,
    viewModel: SearchPlanetsScreenViewModel = koinViewModel()
){
    val state by viewModel.state.observeAsState(SearchPlanetsScreenUiState.Initial)

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
            var namePlanets by remember { mutableStateOf("") }

            OutlinedTextField(
                modifier = Modifier
                    .padding(5.dp, 10.dp, 5.dp, 10.dp)
                    .height(55.dp)
                    .fillMaxWidth(),
                leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
                value = namePlanets,
                onValueChange = {
                    namePlanets = it
                    if (namePlanets.length > 0)
                        viewModel.searchPlanets(namePlanets)
                },
                placeholder = {
                    Text(
                        "Enter the name of the planet",
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
                SearchPlanetsScreenUiState.Initial    -> Unit
                SearchPlanetsScreenUiState.Loading    -> ScreenLoadind()
                is SearchPlanetsScreenUiState.Content -> {
                    ListOfPlanetLazyVerticalGrid(navController = navController, planets = (state as SearchPlanetsScreenUiState.Content).planets.collectAsLazyPagingItems())
                }
                is SearchPlanetsScreenUiState.Error   -> ScreenError(errorText = (state as SearchPlanetsScreenUiState.Error).message.orEmpty())
            }
        }
    }
}

@Composable
fun ListOfPlanetLazyVerticalGrid(
    navController: NavHostController,
    planets: LazyPagingItems<PlanetEntity>
){
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        items(count = planets.itemCount) { index ->
            planets[index]?.let { PlanetBox(navController, it) }
        }
    }
}

@Composable
fun PlanetBox(
    navController: NavHostController,
    planet: PlanetEntity
){
    /*val image =
        rememberAsyncImagePainter(productItem.photo)*/

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
                navController.navigate(Routes.InfoPlanetScreenRoute.route)
            },
        contentAlignment = Alignment.BottomCenter,
    ){
        Image(
            modifier = Modifier
                .size(200.dp),
            painter = painterResource(R.drawable.background_character_image),
            contentScale = ContentScale.Crop,
            contentDescription = "Icon planet"
        )
        Text(
            modifier = Modifier
                .padding(5.dp),
            text = planet.name,
            color = Color.Yellow,
            fontSize = 20.sp
        )
    }
}

@Preview
@Composable
fun SearchPlanetsScreenPreview(){
    val navController = rememberNavController()
    SearchPlanetsScreen(navController)
}
