package com.example.starwars_app.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.starwars_app.R
import com.example.starwars_app.screen.navigation.Routes

@Composable
fun ResourcesScreen(
    navController: NavHostController
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background_image),
                contentScale = ContentScale.FillHeight
            ),
        contentAlignment = Alignment.Center
    ){
        Column(){

            Button(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(300.dp, 55.dp)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(30.dp)
                    ),
                onClick = {
                    navController.navigate(Routes.SearchPlanetsScreenRoute.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(30.dp),
            ){
                Text(
                    text = "Planets",
                    fontSize = 20.sp
                )
            }

            Button(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(300.dp, 55.dp)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(30.dp)
                    ),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(30.dp),
            ){
                Text(
                    text = "Characters",
                    fontSize = 20.sp
                )
            }

            Button(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(300.dp, 55.dp)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(30.dp)
                    ),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(30.dp),
            ){
                Text(
                    text = "Starships",
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun ResourcesScreenPreview(){
    val navController = rememberNavController()
    ResourcesScreen(navController)
}