package com.example.nasaimages.common.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.nasaimages.common.contentDescription
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NasaImage(modifier: Modifier = Modifier, href: String,contentDescription: String) {
    GlideImage(
        modifier = modifier,
        imageModel = { href }, // loading a network image using an URL.
        imageOptions = ImageOptions(
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
    )
}