package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.example.newsapp.ui.theme.MyApplicationTheme
import androidx.compose.foundation.layout.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.screens.*

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<DataLoaderViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadNews()
        viewModel.posts.observe(this) { postsResult ->
            setContent {
                MyApplicationTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = NewsScreens.HomeScreen.route
                        ){
                            when (postsResult) {
                                is Result.Success -> {
                                    val posts = postsResult.data.body()?.articles.orEmpty()

                                    //Home Screen
                                    composable(route = NewsScreens.HomeScreen.route) {

                                        News(
                                            navController = navController,
                                            posts = posts,
                                            onRefresh = viewModel::loadNews,
                                            onSearch = viewModel::search,
                                            onSelectFilter = {
                                                viewModel.loadNewsWithCategories(it)
                                            }
                                        )
                                    }

                                    // Details Screen
                                    composable(
                                        route = NewsScreens.DetailScreen.route + "/{index}",
                                        arguments = listOf(navArgument(name = "index") {
                                            type = NavType.IntType
                                        })
                                    ) { entry ->
                                        val index = entry.arguments?.getInt("index")

                                        index?.let {
                                            posts[it]
                                        }?.let {
                                            UserDetailsScreen(navController, it)
                                        }

                                    }

                                }

                                else -> {

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}





