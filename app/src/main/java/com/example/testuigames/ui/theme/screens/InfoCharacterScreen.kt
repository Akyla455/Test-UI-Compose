package com.example.testuigames.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.testuigames.model.InfoCharacters

@Composable
fun InfoCharactersScreen(
    info: InfoCharacters
){
    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier.fillMaxHeight(),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(info.image)
                        .crossfade(true)
                        .build(),
            contentDescription = null
        )
        info.name?.let {
            Text(
                modifier = Modifier.padding(
                start = 8.dp,
                top = 20.dp),
                text = it,
                fontSize = 24.sp
            )
        }
        info.species?.let {
            Text(
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 10.dp
                    ),
                text = it,
                fontSize = 24.sp
                )
        }
        Text(text = info.episode.toString())
    }
}