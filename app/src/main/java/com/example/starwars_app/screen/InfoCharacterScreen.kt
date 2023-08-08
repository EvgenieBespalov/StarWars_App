package com.example.starwars_app.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starwars_app.R

@Composable
fun InfoCharacterScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background_image),
                contentScale = ContentScale.FillHeight
            )
    ){
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
        ){
            Text(
                text = "Character name",
                fontSize = 30.sp,
                color = Color.Yellow
            )
            Image(
                modifier = Modifier
                    .size(400.dp)
                    .graphicsLayer {
                        clip = true
                        shape = RoundedCornerShape(30.dp)
                    }
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(30.dp)
                    ),
                painter = painterResource(R.drawable.background_character_image),
                contentScale = ContentScale.Crop,
                contentDescription = "Icon character"
            )
            Text(
                text = "Gender",
                fontSize = 30.sp,
                color = Color.Yellow
            )
            Text(
                text = "Starships count",
                fontSize = 30.sp,
                color = Color.Yellow
            )
        }
    }
}