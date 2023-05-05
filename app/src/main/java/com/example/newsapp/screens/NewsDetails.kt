package com.example.newsapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.ArticleResponse
import androidx.compose.foundation.Image
import androidx.compose.ui.text.TextStyle

@Composable
fun UserDetailsScreen(navController: NavController, post: ArticleResponse) {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "News Detail", fontWeight = FontWeight.Bold)
            }
        }
    }) {it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Source: "+post.source.name)
            post.author?.let { it1 -> Text(text = "Author: "+it1) }

            Image(
                painter = rememberAsyncImagePainter(model = post.urlToImage),
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
                    .fillMaxWidth()
                )

            Text(text = post.title, style = TextStyle(fontWeight = FontWeight.Bold))
            post.content?.let { it1 -> Text(text = it1) }

        }
    }
}