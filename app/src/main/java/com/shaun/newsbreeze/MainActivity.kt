package com.shaun.newsbreeze

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shaun.newsbreeze.localdatabase.toArticleLocal
import com.shaun.newsbreeze.models.Article
import com.shaun.newsbreeze.navigation.Routes
import com.shaun.newsbreeze.presentation.screens.HomeScreen
import com.shaun.newsbreeze.presentation.screens.NewsViewScreen
import com.shaun.newsbreeze.ui.theme.NewsBreezeTheme
import com.shaun.newsbreeze.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val homeViewModel: HomeViewModel = viewModel()
            NewsBreezeTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colors.background) {


                    val navController = rememberNavController()


                    NavHost(
                        navController = navController,
                        startDestination = Routes.HomeScreen.route
                    ) {

                        composable(Routes.HomeScreen.route) {
                            HomeScreen(homeViewModel, onReadClicked = { article ->
                                navController.currentBackStackEntry?.arguments = Bundle().apply {
                                    putSerializable("article", article)
                                }
                                navController.navigate(Routes.NewsViewScreen.route)

                            }, onSaveClicked = { item ->
                                homeViewModel.insertArticle(
                                    articleLocal = item.toArticleLocal()
                                )
                            }, onDeleteClicked = {
                                Toast.makeText(this@MainActivity, "Unsaved", Toast.LENGTH_SHORT).show()
                                homeViewModel.deleteArticle(it.title)
                            })
                        }
                        composable(Routes.NewsViewScreen.route) {

                            var article =
                                navController.previousBackStackEntry?.arguments?.getSerializable("article") as Article




                            Log.d("TAG", "onCreate: $article")
                            EnterAnimation {
                                NewsViewScreen(article, onBackPressed = {
                                    navController.popBackStack()
                                }, onSaveClicked = { item ->
                                    homeViewModel.insertArticle(
                                        articleLocal = item.toArticleLocal()
                                    )
                                }, homeViewModel)
                            }
                        }
                    }
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
            initialOffsetY = { 1000 }
        ),
        exit = slideOutVertically(
//            targetOffsetY = { 1000 }
        ),
        content = content,
        initiallyVisible = false
    )

}