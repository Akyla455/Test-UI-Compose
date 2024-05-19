package com.example.testuigames.ui.theme.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.testuigames.R
import com.example.testuigames.model.InfoCharacters
import com.example.testuigames.viewModels.InfoCharactersViewModel
import com.example.testuigames.viewModels.InfoState
import org.koin.androidx.compose.koinViewModel

@Composable
fun InfoApp(
    infoCharactersViewModel: InfoCharactersViewModel = koinViewModel()
){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        InfoScreenState(infoState = infoCharactersViewModel.infoState)
    }
}

@Composable
fun InfoScreenState(
    infoState: InfoState
) {
    when (infoState) {
        is InfoState.Error -> ErrorScreen()
        is InfoState.Loading -> LoadingScreen()
        is InfoState.Success -> InfoList(info = infoState.infoCharacter)
    }
}

@Composable
fun InfoList(
    info: List<InfoCharacters>
) {
    LazyColumn {
        itemsIndexed(info){_, info ->
            InfoCharactersScreen(info = info)
        }
    }
}

@Composable
fun InfoCharactersScreen(
    info: InfoCharacters
//    infoCharactersId: Int?,
//    charactersRepository: CharactersRepository = get()
) {
//    val info = infoCharactersId?.let {
//        charactersRepository.getCharacterById(it)
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize()
            .background(Color.Gray)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(info.image)
                        .crossfade(true)
                        .build(),
            contentDescription = null
        )
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(
                       start = 8.dp,
                       end = 8.dp,
                       top = 20.dp
                   )
           ) {
               Text(
                   stringResource(R.string.name_character),
                   style = TextStyle(
                       fontSize = 22.sp,
                       color = Color.Black,
                       fontWeight = FontWeight.Bold
                   )
               )
               info.name?.let {
                   Text(
                       text = it,
                       fontSize = 22.sp
                   )
               }
           }
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(
                       start = 8.dp,
                       end = 8.dp,
                       top = 10.dp
                   )) {
               Text(
                   stringResource(R.string.species_character),
                   style = TextStyle(
                       fontSize = 22.sp,
                       color = Color.Black,
                       fontWeight = FontWeight.Bold
                   )
               )
               info.species?.let {
                   Text(
                       text = it,
                       fontSize = 22.sp
                   )
               }
           }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 10.dp
                )
        ) {
            Text(
                stringResource(R.string.episode_character),
                style = TextStyle(
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = info.episode.toString(),
                fontSize = 16.sp
            )
        }
    }
}