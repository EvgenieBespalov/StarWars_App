package com.example.starwars_app.screen

import androidx.compose.foundation.Image
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
import com.example.starwars_app.R
import com.example.starwars_app.screen.navigation.Routes

@Composable
fun SearchCharactersScreen(navController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background_image),
                contentScale = ContentScale.FillHeight
            ),
        contentAlignment = Alignment.TopCenter
    ){
        Column(modifier = Modifier
            .fillMaxSize()
        ){
            var userPasswordTextField by remember { mutableStateOf("") }
            var userPasswordCorrectTextField by remember { mutableStateOf(false) }

            OutlinedTextField(
                modifier = Modifier
                    .padding(5.dp, 10.dp, 5.dp, 10.dp)
                    .height(55.dp)
                    .fillMaxWidth(),
                leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
                value = userPasswordTextField,
                onValueChange = {

                },
                isError = userPasswordCorrectTextField,
                placeholder = {
                    Text(
                        "Enter the name of the character",
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

            ListOfCharactersLazyVerticalGrid(navController)
        }
    }
}

@Composable
fun ListOfCharactersLazyVerticalGrid(
    navController: NavHostController
){
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        items(100){
            CharacterBox(navController)
        }
    }
}

@Composable
fun CharacterBox(
    navController: NavHostController
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
                navController.navigate(Routes.PlanetInfoScreenRoute.route)
            },
        contentAlignment = Alignment.BottomCenter,
    ){
        Image(
            modifier = Modifier
                .size(200.dp),
            painter = painterResource(R.drawable.background_character_image),
            contentScale = ContentScale.Crop,
            contentDescription = "Icon character"
        )
        Text(
            modifier = Modifier
                .padding(5.dp),
            text = "Name character",
            color = Color.Yellow,
            fontSize = 20.sp
        )
    }
}