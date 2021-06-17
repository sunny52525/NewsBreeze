package com.shaun.newsbreeze.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.rememberGlidePainter
import com.shaun.newsbreeze.utils.AppConstants

@ExperimentalMaterialApi
@Composable
fun NewsViewTopSection(
    imageUrl: String = AppConstants.dummyImage,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Box {
        Image(
            painter = rememberGlidePainter(request = imageUrl), contentDescription = null,

            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )

    }
}