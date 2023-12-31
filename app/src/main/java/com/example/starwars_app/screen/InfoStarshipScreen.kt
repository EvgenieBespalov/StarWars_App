package com.example.starwars_app.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import coil.compose.AsyncImage
import com.example.starwars_app.R
import com.example.starwars_app.domain.entity.StarshipEntity
import com.example.starwars_app.presentation.InfoPlanetScreenUiState
import com.example.starwars_app.presentation.InfoPlanetScreenViewModel
import com.example.starwars_app.presentation.InfoStarshipScreenUiState
import com.example.starwars_app.presentation.InfoStarshipScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun InfoStarshipScreen(
    viewModel: InfoStarshipScreenViewModel = koinViewModel(),
    starshipId: String?
){
    val state by viewModel.state.observeAsState(InfoStarshipScreenUiState.Initial)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background_image),
                contentScale = ContentScale.None
            )
    ){
        when(state){
            InfoStarshipScreenUiState.Initial    -> starshipId?.let { viewModel.getStarshipBy(it) }
            InfoStarshipScreenUiState.Loading    -> ScreenLoadind()
            is InfoStarshipScreenUiState.Content -> {
                StarshipInfoColumn(starship = (state as InfoStarshipScreenUiState.Content).starship, checkSave = (state as InfoStarshipScreenUiState.Content).checkFavorites)
            }
            is InfoStarshipScreenUiState.Error   -> ScreenError(errorText = (state as InfoStarshipScreenUiState.Error).message.orEmpty())
        }

    }
}

@Composable
fun StarshipInfoColumn(
    viewModel: InfoStarshipScreenViewModel = koinViewModel(),
    checkSave: Boolean,
    starship: StarshipEntity
){
    Column(
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
    ){
        Row(){
            Text(
                modifier = Modifier
                    .weight(8f)
                    .fillMaxWidth(),
                text = starship.name,
                fontSize = 30.sp,
                color = Color.Yellow
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                IconButton(
                    modifier = Modifier
                        .size(40.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.LightGray,
                        disabledContentColor = Color.Yellow
                    ),
                    enabled = checkSave.not(),
                    onClick = {
                        viewModel.saveStarship(starship)
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(40.dp),
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Buttom add in favorites"
                    )
                }
            }
        }
        AsyncImage(
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
            model = starship.image,
            error = painterResource(R.drawable.placeholder_image),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Icon planet"
        )
        Text(
            text = "Model: ${starship.model}",
            fontSize = 30.sp,
            color = Color.Yellow
        )
        Text(
            text = "Manufacturer: ${starship.manufacturer}",
            fontSize = 30.sp,
            color = Color.Yellow
        )
        Text(
            text = "Passengers: ${starship.passengers}",
            fontSize = 30.sp,
            color = Color.Yellow
        )
    }
}
