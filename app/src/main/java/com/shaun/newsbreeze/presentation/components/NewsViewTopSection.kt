package com.shaun.newsbreeze.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.imageloading.ImageLoadState
import com.shaun.newsbreeze.R
import com.shaun.newsbreeze.utils.AppConstants

@ExperimentalMaterialApi
@Composable
fun NewsViewTopSection(
    imageUrl: String = AppConstants.dummyImage,

) {
    Box {
        val painter = rememberGlidePainter(imageUrl, fadeIn = true)


        Image(
            painter = painter, contentDescription = null,

            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )

        when (painter.loadState) {


            is ImageLoadState.Loading -> {

                ShimmerItem(
                    size = 200,
                    showBottomLine = false,
                    paddingStart = 0,
                    paddingEnd = 0,
                    paddingTop = 0
                )
            }
            is ImageLoadState.Error -> {

                Image(
                    painter = painterResource(id = R.drawable.failed),
                    null,
                    Modifier
                        .height(400.dp)
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
        }

    }
}