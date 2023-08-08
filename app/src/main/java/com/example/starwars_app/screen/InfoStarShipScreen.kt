package com.example.starwars_app.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starwars_app.R

@Composable
fun InfoStarShipScreen(){
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
                text = "Starship name",
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
                contentDescription = "Icon starship"
            )
            Text(
                text = "Model",
                fontSize = 30.sp,
                color = Color.Yellow
            )
            Text(
                text = "Manufacturer",
                fontSize = 30.sp,
                color = Color.Yellow
            )
            Text(
                text = "Passengers",
                fontSize = 30.sp,
                color = Color.Yellow
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                IconButton(
                    modifier = Modifier
                        .size(75.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Yellow,
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(75.dp),
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Buttom add in favorites"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun InfoStarShipScreenPreview(){
    InfoStarShipScreen()
}
