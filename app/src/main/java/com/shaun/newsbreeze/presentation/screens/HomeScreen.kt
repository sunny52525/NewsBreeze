package com.shaun.newsbreeze.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shaun.newsbreeze.presentation.components.Header
import com.shaun.newsbreeze.presentation.components.HomeNewsItem
import com.shaun.newsbreeze.presentation.components.SearchBar
import com.shaun.newsbreeze.ui.theme.BackgroundColorBreeze

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen() {


    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(BackgroundColorBreeze)
    ) {


        item {
            Spacer(modifier = Modifier.height(50.dp))
            Header()
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            SearchBar() {

            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            HomeNewsItem()
        }
        item {
            HomeNewsItem()
        }
        item {
            HomeNewsItem()
        }
        item {
            HomeNewsItem()
        }
        item {
            HomeNewsItem()
        }
        item {
            HomeNewsItem()
        }
        item {
            HomeNewsItem()
        }
        item {
            HomeNewsItem()
        }
        item {
            HomeNewsItem()
        }
        item {
            HomeNewsItem()
        }
        item {
            HomeNewsItem()
        }
    }
}