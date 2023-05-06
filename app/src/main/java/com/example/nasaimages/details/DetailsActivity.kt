package com.example.nasaimages.details

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nasaimages.R
import com.example.nasaimages.common.KEY_DATE
import com.example.nasaimages.common.KEY_DESCRIPTION
import com.example.nasaimages.common.KEY_IMAGE_URL
import com.example.nasaimages.common.KEY_TITLE
import com.example.nasaimages.common.composables.NasaImage
import com.example.nasaimages.ui.theme.NasaImagesTheme

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val detailsUi = DetailsUi(
            href = intent.getStringExtra(KEY_IMAGE_URL),
            date = intent.getStringExtra(KEY_DATE) ?: "Undated images",
            title = intent.getStringExtra(KEY_TITLE) ?: "Untitled Image",
            description = intent.getStringExtra(KEY_DESCRIPTION) ?: "No description"
        )
        setContent {
            NasaImagesTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = detailsUi.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
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
                    content = { paddingValues ->
                        val configuration = LocalConfiguration.current
                        when (configuration.orientation) {
                            Configuration.ORIENTATION_PORTRAIT -> DetailColumn(
                                Modifier.padding(paddingValues),
                                detailsUi
                            )
                            Configuration.ORIENTATION_LANDSCAPE -> DetailRow(
                                Modifier.padding(paddingValues),
                                detailsUi)
                            else -> DetailColumn(
                                Modifier.padding(paddingValues),
                                detailsUi
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun DetailColumn(modifier: Modifier, detailsUi: DetailsUi) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        detailsUi.href?.let {
            NasaImage(
                modifier = Modifier.aspectRatio(9f / 9f),
                href = it,
                contentDescription = detailsUi.description
            )
        }
        TextDetails(detailsUi)
    }
}

@Composable
fun DetailRow(modifier: Modifier, detailsUi: DetailsUi) {
    Row(modifier = modifier.fillMaxSize()) {
        detailsUi.href?.let {
            NasaImage(
                modifier = Modifier
                    .aspectRatio(9f / 9f)
                    .weight(0.5f),
                href = it,
                contentDescription = detailsUi.description
            )
        }
        Column( modifier = Modifier
            .verticalScroll(rememberScrollState())
            .weight(0.5f)
        ) {
            TextDetails(detailsUi)
        }
    }
}

@Composable
fun TextDetails(detailsUi: DetailsUi) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = detailsUi.title,
        fontWeight = FontWeight.ExtraBold,
        overflow = TextOverflow.Ellipsis,
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = detailsUi.description,
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = detailsUi.date,
        fontSize = 12.sp
    )
}

data class DetailsUi(
    val title: String,
    val description: String,
    val href: String?,
    val date: String
)