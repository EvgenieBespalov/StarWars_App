package com.example.starwars_app.screen

import androidx.compose.foundation.Image
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
import coil.compose.AsyncImage
import com.example.starwars_app.R
import com.example.starwars_app.domain.entity.PeopleEntity
import com.example.starwars_app.presentation.InfoPeopleScreenUiState
import com.example.starwars_app.presentation.InfoPeopleScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun InfoPeopleScreen(
    viewModel: InfoPeopleScreenViewModel = koinViewModel(),
    peopleId: String?
){
    val state by viewModel.state.observeAsState(InfoPeopleScreenUiState.Initial)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background_image),
                contentScale = ContentScale.None
            )
    ){
        when(state){
            InfoPeopleScreenUiState.Initial    -> peopleId?.let { viewModel.getPeopleBy(it) }
            InfoPeopleScreenUiState.Loading    -> ScreenLoadind()
            is InfoPeopleScreenUiState.Content -> {
                InfoPeopleColumn(people = (state as InfoPeopleScreenUiState.Content).people)
            }
            is InfoPeopleScreenUiState.Error   -> ScreenError(errorText = (state as InfoPeopleScreenUiState.Error).message.orEmpty())
        }
    }
}

@Composable
fun InfoPeopleColumn(people: PeopleEntity){
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
    ){
        Text(
            text = people.name,
            fontSize = 30.sp,
            color = Color.Yellow
        )
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
            model = people.image,
            error = painterResource(R.drawable.placeholder_image),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Icon planet"
        )
        Text(
            text = "Gender: ${people.gender}",
            fontSize = 30.sp,
            color = Color.Yellow
        )
        Text(
            text = "Starships count: ${people.countOfStarhips}",
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