package com.example.newsapp.screens



import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newsapp.ArticleResponse

@Composable
fun News(navController:  NavHostController,
                posts: List<ArticleResponse>,
                onRefresh: () -> Unit,
                onSearch: (String) ->Unit,
                onSelectFilter: (String) -> Unit) {
    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            FilterButton(
                filters = listOf(
                    Filter(1, "Business"),
                    Filter(2, "Entertainment"),
                    Filter(3, "General"),
                    Filter(4, "Health"),
                ),
                onFilterSelected = { filter ->
                    onSelectFilter(filter.name)  // API Request with filter
                }
            )

            SearchBar(onSearch = { query ->
                onSearch(query)
                Log.d(">>>", query)
            })
        }

        NewsRefreshableLazyColumn(
            data = posts,
            onRefresh = onRefresh,
            navController = navController
        )


    }
}