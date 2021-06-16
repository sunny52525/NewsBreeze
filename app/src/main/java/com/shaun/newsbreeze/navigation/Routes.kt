package com.shaun.newsbreeze.navigation


sealed class Routes(val route: String) {
    object HomeScreen : Routes("HomeScreen")
    object SearchScreen : Routes("SearchScreen")
    object SavedScreen : Routes("SavedScreen")

}