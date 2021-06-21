package com.shaun.newsbreeze

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.shaun.newsbreeze.ui.theme.HeadingFont
import com.shaun.newsbreeze.ui.theme.LightGreen

@ExperimentalFoundationApi
@Composable

fun SplashActivity() {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "News Breeze",
                fontFamily = HeadingFont,
                color = LightGreen,
                fontSize = 30.sp,

                )
        }
    }


}