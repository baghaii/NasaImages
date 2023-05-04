package com.example.nasaimages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val state by mainViewModel.state.collectAsState()
    if (state.isLoading) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchBar()
            MainScreenList(
                data = state.data?.collection?.items ?: emptyList()
            )
        }
    }
}

@Composable
fun MainScreenList(
    data: List<Item>
) {
    val state = rememberLazyListState()

    LazyColumn{
        items(data.size) {
            RowContent(
                href = data[it].links.first().href,
                title = data[it].data.first().title
            )
        }
    }
}

@Composable
fun RowContent(
    href: String,
    title: String
) {
    Box{
        GlideImage(
            modifier = Modifier.fillMaxWidth(),
            imageModel = { href }, // loading a network image using an URL.
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            text = title,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.inversePrimary
        )
    }
}

@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .wrapContentHeight(align = Alignment.Top)
                .padding(start = 16.dp, bottom = 8.dp),
            value = text,
            onValueChange = { text = it },
            label = { Text("Nasa Image Search") }
        )
        Button(
            modifier = Modifier.width(72.dp).align(Alignment.CenterVertically),
            onClick = { }
        ) {
            Text(stringResource(id = R.string.go))
        }
    }
}