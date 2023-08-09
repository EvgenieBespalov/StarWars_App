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
import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.presentation.SearchPeoplesScreenUiState
import com.example.starwars_app.presentation.SearchPeoplesScreenViewModel
import com.example.starwars_app.presentation.SearchPlanetsScreenUiState
import com.example.starwars_app.screen.navigation.Routes
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchPeoplesScreen(
    navController: NavHostController,
    viewModel: SearchPeoplesScreenViewModel = koinViewModel()
){
    val state by viewModel.state.observeAsState(SearchPeoplesScreenUiState.Initial)

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
            var namePeoples by remember { mutableStateOf("") }

            OutlinedTextField(
                modifier = Modifier
                    .padding(5.dp, 10.dp, 5.dp, 10.dp)
                    .height(55.dp)
                    .fillMaxWidth(),
                leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
                value = namePeoples,
                onValueChange = {
                    namePeoples = it
                    if (namePeoples.length > 1)
                        viewModel.searchPeoples(namePeoples)
                },
                placeholder = {
                    Text(
                        "Enter the name of the people",
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
                SearchPeoplesScreenUiState.Initial    -> Unit
                SearchPeoplesScreenUiState.Loading    -> ScreenLoadind()
                is SearchPeoplesScreenUiState.Content -> {
                    ListOfPeoplesLazyVerticalGrid(navController = navController, peoples = (state as SearchPeoplesScreenUiState.Content).peoples.collectAsLazyPagingItems())
                }
                is SearchPeoplesScreenUiState.Error   -> ScreenError(errorText = (state as SearchPlanetsScreenUiState.Error).message.orEmpty())
            }
        }
    }
}

@Composable
fun ListOfPeoplesLazyVerticalGrid(
    navController: NavHostController,
    peoples: LazyPagingItems<PeopleEntity>
){
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        items(count = peoples.itemCount) { index ->
            peoples[index]?.let { PeopleBox(navController, it) }
        }
    }
}

@Composable
fun PeopleBox(
    navController: NavHostController,
    people: PeopleEntity
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
                navController.navigate(Routes.InfoPeopleScreenRoute.route + "/${people.id}")
            },
        contentAlignment = Alignment.BottomCenter,
    ){
        AsyncImage(
            modifier = Modifier
                .size(200.dp),
            model = people.image,
            error = painterResource(R.drawable.placeholder_image),
            contentScale = ContentScale.Crop,
            contentDescription = "Icon planet"
        )
        Text(
            modifier = Modifier
                .padding(5.dp),
            text = people.name,
            color = Color.Yellow,
            fontSize = 20.sp
        )
    }
}