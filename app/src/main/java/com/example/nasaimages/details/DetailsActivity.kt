package com.example.nasaimages.details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nasaimages.R
import com.example.nasaimages.constants.KEY_DATE
import com.example.nasaimages.constants.KEY_DESCRIPTION
import com.example.nasaimages.constants.KEY_IMAGE_URL
import com.example.nasaimages.constants.KEY_TITLE
import com.example.nasaimages.ui.theme.NasaImagesTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val href = intent.getStringExtra(KEY_IMAGE_URL)
        val date = intent.getStringExtra(KEY_DATE) ?: "Undated images"
        val title = intent.getStringExtra(KEY_TITLE) ?: "Untitled Image"
        val description = intent.getStringExtra(KEY_DESCRIPTION) ?: "No description"
        setContent {
            NasaImagesTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = { onBackPressedDispatcher.onBackPressed() }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = stringResource(R.string.back)
                                    )
                                }
                            }
                        )
                    },
                    content = {
                        Column(
                            modifier = Modifier.verticalScroll(rememberScrollState())
                        ) {
                            href?.let {
                                GlideImage(
                                    modifier = Modifier
                                        .aspectRatio(9f / 9f),
                                    imageModel = { it }, // loading a network image using an URL.
                                    imageOptions = ImageOptions(
                                        contentScale = ContentScale.Crop,
                                        alignment = Alignment.Center
                                    )
                                )
                            }
                            TextDetails(title, description, date)
                        }
                    }
                )
            }

        }
    }
}


@Composable
fun TextDetails(title: String, description: String, date: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = title,
        fontWeight = FontWeight.ExtraBold,
        overflow = TextOverflow.Ellipsis,
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = description,
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = date,
        fontSize = 12.sp
    )
}