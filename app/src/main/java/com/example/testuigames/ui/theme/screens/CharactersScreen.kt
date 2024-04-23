package com.example.testuigames.ui.theme.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.testuigames.model.InfoCharacters
import com.example.testuigames.viewModels.CharactersUiState
import com.example.testuigames.viewModels.CharactersViewModel
import java.nio.file.WatchEvent

@Composable
fun CharactersApp() {
   val charactersViewModel: CharactersViewModel =
      viewModel(factory = CharactersViewModel.Factory)
   Surface(
      modifier = Modifier.fillMaxSize()
   ) {
      HomeScreen(charactersUiState = charactersViewModel.charactersUiState)
   }
}

@Composable
fun HomeScreen(
   charactersUiState: CharactersUiState
) {
   when (charactersUiState) {
      is CharactersUiState.Loading -> LoadingScreen()
      is CharactersUiState.Success -> CharactersList(
         infoCharacters = charactersUiState.charactersSearch
      )
      is CharactersUiState.Error -> ErrorScreen()
   }
}

@Composable
fun CharactersList(
   infoCharacters: List<InfoCharacters>
) {
   LazyColumn {
      itemsIndexed(infoCharacters) { _, character ->
         CardCharacters(infoCharacters = character)
      }
   }
}

@Composable
fun CardCharacters(
   infoCharacters: InfoCharacters
) {
   Card(
      modifier = Modifier
         .fillMaxWidth()
         .padding(5.dp)
         .requiredHeight(100.dp)
   ) {
      Row(
         verticalAlignment = Alignment.CenterVertically
      ) {
         AsyncImage(
            modifier = Modifier
               .padding(8.dp)
               .size(90.dp),
            model = ImageRequest.Builder(context = LocalContext.current)
               .data(infoCharacters.image)
               .crossfade(true)
               .build(), contentDescription = null
         )
         infoCharacters.name?.let {
            Text(
               modifier = Modifier.padding(15.dp),
               text = it,
               fontFamily = FontFamily.Cursive,
               fontSize = 24.sp
            )
         }
      }
   }
}
