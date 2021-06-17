package com.shaun.newsbreeze.presentation.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shaun.newsbreeze.models.Article
import com.shaun.newsbreeze.models.NewsArticles
import com.shaun.newsbreeze.presentation.components.*
import com.shaun.newsbreeze.ui.theme.BackgroundColorBreeze
import com.shaun.newsbreeze.viewmodels.HomeViewModel

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onReadClicked: (Article) -> Unit,
    onSaveClicked: (Article) -> Unit
) {

    val topHeadlines: NewsArticles? by homeViewModel.topHeadlines.observeAsState(NewsArticles())

    val noItemsFound: Boolean by homeViewModel.searchFailed.observeAsState(false)

    homeViewModel.searchFailed.observeForever {
        Log.d("TAG", "HomeScreen: $it")
        homeViewModel.isInSearchMode.postValue(!it)

    }

    homeViewModel.topHeadlines.observeForever {
        if (it.articles.isNotEmpty()) {
            homeViewModel.isInSearchMode.postValue(false)
        }
    }

    Log.d("TAG", "HomeScreen: ${topHeadlines?.totalResults}")

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
        stickyHeader {
            SearchBar() {
                Log.d("TAG", "HomeScreen: $it")
                homeViewModel.searchNews(it)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        if (noItemsFound) {
            item { NotFoundScreen() }
        } else
            if (homeViewModel.isInSearchMode.value == true|| topHeadlines?.totalResults!! <=0) {
                topHeadlines?.totalResults?.let {
                    if (it > 2) {
                        homeViewModel.isInSearchMode.postValue(false)
                    }
                }

                repeat(4) {
                    item {
                        EnterAnimation {
                            ShimmerAnimation()
                        }
                    }
                }
            }
        else
        topHeadlines?.let {
            itemsIndexed(it.articles) { index, item ->

                    EnterAnimation {
                        HomeNewsItem(
                            title = item.title,
                            shortDescription = item.description,
                            imageUrl = item.urlToImage,
                            date = item.publishedAt.substring(0, 10),
                            onReadClick = {
                                onReadClicked(item)
                            }
                        )
                    }

            }

        }
    }
}


@ExperimentalAnimationApi
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {


    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { -100 }
        ),
        exit = slideOutVertically(
            targetOffsetY = { 100 }
        ),
        content = content,
        initiallyVisible = false
    )

}