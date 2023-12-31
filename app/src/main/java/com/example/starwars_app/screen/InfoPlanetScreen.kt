package com.example.starwars_app.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.starwars_app.R
import com.example.starwars_app.domain.entity.PlanetEntity
import com.example.starwars_app.presentation.InfoPlanetScreenUiState
import com.example.starwars_app.presentation.InfoPlanetScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun InfoPlanetScreen(
    viewModel: InfoPlanetScreenViewModel = koinViewModel(),
    planetId: String?
){
    val state by viewModel.state.observeAsState(InfoPlanetScreenUiState.Initial)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background_image),
                contentScale = ContentScale.None
            )
    ){
        when(state){
            InfoPlanetScreenUiState.Initial    -> planetId?.let { viewModel.getPlanetById(it) }
            InfoPlanetScreenUiState.Loading    -> ScreenLoadind()
            is InfoPlanetScreenUiState.Content -> {
                InfoPlanetColumn(planet = (state as InfoPlanetScreenUiState.Content).planet, checkSave = (state as InfoPlanetScreenUiState.Content).checkFavorites)
            }
            is InfoPlanetScreenUiState.Error   -> ScreenError(errorText = (state as InfoPlanetScreenUiState.Error).message.orEmpty())
        }
    }
}

@Composable
fun InfoPlanetColumn(
    viewModel: InfoPlanetScreenViewModel = koinViewModel(),
    checkSave: Boolean,
    planet: PlanetEntity
) {
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
    ){
        Row(){
            Text(
                modifier = Modifier
                    .weight(8f)
                    .fillMaxWidth(),
                text = planet.name,
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
                        viewModel.savePlanet(planet)
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
            model = planet.image,
            error = painterResource(R.drawable.placeholder_image),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Icon planet"
        )
        Text(
            text = "Diameter: ${planet.diameter}",
            fontSize = 30.sp,
            color = Color.Yellow
        )
        Text(
            text = "Population: ${planet.population}",
            fontSize = 30.sp,
            color = Color.Yellow
        )
    }
}
