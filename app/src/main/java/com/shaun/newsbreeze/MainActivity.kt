package com.shaun.newsbreeze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shaun.newsbreeze.navigation.Routes
import com.shaun.newsbreeze.presentation.screens.HomeScreen
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
                            HomeScreen(homeViewModel)
                        }
                    }
                }
            }
        }
    }
}
