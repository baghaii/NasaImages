package com.example.nasaimages.main

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nasaimages.networking.Datum
import com.example.nasaimages.networking.Item
import com.example.nasaimages.LoadingScreen
import com.example.nasaimages.R
import com.example.nasaimages.constants.KEY_DATE
import com.example.nasaimages.constants.KEY_DESCRIPTION
import com.example.nasaimages.constants.KEY_IMAGE_URL
import com.example.nasaimages.constants.KEY_TITLE
import com.example.nasaimages.details.DetailsActivity
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
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
                data = state.data?.collection?.items ?: emptyList(),
            )
        }
    }
}

@Composable
fun MainScreenList(
    data: List<Item>,
) {
    val state = rememberLazyListState()

    LazyColumn(state = state){
        items(data.size) {
            RowContent(
                href = data[it].links.first().href,
                datum = data[it].data.first(),
            )
        }
    }
}

@Composable
fun RowContent(
    href: String,
    datum: Datum,
) {
    val context = LocalContext.current
    Box(modifier = Modifier
        .padding(bottom = 8.dp, start = 12.dp, end = 12.dp)
        .clip(
            RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 6.dp,
                bottomEnd = 6.dp
            )
        )
        .border(width = 2.dp, color = MaterialTheme.colorScheme.primary)
        .clickable {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(KEY_IMAGE_URL, href)
            intent.putExtra(KEY_TITLE, datum.title)
            intent.putExtra(KEY_DESCRIPTION, datum.description)
            intent.putExtra(KEY_DATE, datum.date_created)
            context.startActivity(intent)
        }
    ){
        GlideImage(
            modifier = Modifier
                .aspectRatio(12f / 9f),
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
            text = datum.title,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
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
            modifier = Modifier
                .width(72.dp)
                .align(Alignment.CenterVertically),
            onClick = { }
        ) {
            Text(stringResource(id = R.string.go))
        }
    }
}