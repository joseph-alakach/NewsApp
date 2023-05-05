package com.example.newsapp.screens

sealed class NewsScreens(val route: String) {
    object HomeScreen : NewsScreens("home_screen")
    object DetailScreen : NewsScreens("detail_screen")
}