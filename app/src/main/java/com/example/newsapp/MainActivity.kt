package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.MyApplicationTheme
import retrofit2.Response
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import coil.compose.rememberImagePainter


class MainActivity : ComponentActivity() {
    val viewModel by viewModels<DataLoaderViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val postsResult = viewModel.posts.observeAsState(Result.loading()).value
                    Column {
                        Button(onClick = { viewModel.loadNews() }) {
                            Text(text = "Get Posts")
                        }
                        PostList(postsResult)
                    }
                }
            }
        }
    }
}

@Composable
fun PostList(postsResult: Result<Response<NewsResponse>>) {
    when (postsResult) {
        is Result.Success -> {
            val posts = postsResult.data.body()?.articles.orEmpty()
            LazyColumn {
                items(posts) { post ->
                    Card(
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            Modifier.padding(8.dp)
                        ) {
                            ImageWithUrl(url = post.urlToImage)
                            Text(text = post.title, style = MaterialTheme.typography.h5)
                            post.content?.let { Text(text = it, style = MaterialTheme.typography.body1) }
                        }
                    }
                }
            }
        }
        is Result.Error -> {
            Text(text = "Error: ${postsResult.exception.message}")
        }
        else -> {

        }
    }
}
@Composable
fun ImageWithUrl(url: String?) {
    Image(
        painter = rememberImagePainter(url),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(vertical = 8.dp)
    )
}




