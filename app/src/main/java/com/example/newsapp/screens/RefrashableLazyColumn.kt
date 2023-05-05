package com.example.newsapp.screens



import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun NewsRefreshableLazyColumn(
    data: List<ArticleResponse>,
    onRefresh: () -> Unit,
    navController: NavController,
) {
    val state = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = state,
        onRefresh = {
            onRefresh()
            state.isRefreshing = false
        }
    ) {
        PostsList(navController, data)
    }
}

@Composable
fun PostsList(navController: NavController, posts: List<ArticleResponse>) {
    if (posts.isEmpty()){

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "No Results Found",
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }else{
        LazyColumn {
            itemsIndexed(posts) { index, post ->
                NewsCard(post = post) {
                    navController.navigate(NewsScreens.DetailScreen.route + "/$index")
                }
            }
        }
    }
}

@Composable
private fun NewsCard(post: ArticleResponse, onClick: () -> Unit) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onClick)
            .clip(shape = RoundedCornerShape(10.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = post.urlToImage),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .fillMaxWidth()
            )

            Column {

                Text(post.title)
                post.author?.let { Text(text = it, style = MaterialTheme.typography.body1) }

            }
        }
    }
}