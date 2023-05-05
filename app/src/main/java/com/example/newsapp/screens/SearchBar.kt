package com.example.newsapp.screens


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var searchValue by remember { mutableStateOf(TextFieldValue()) }

    Card(
        backgroundColor = Color.White,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row {
            TextField(
                value = searchValue,
                onValueChange = {
                    searchValue = it
                    //    onSearch(it.text) in case you want to handle search on type
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch(searchValue.text)
                    }
                ),
                placeholder = {
                    Text("Search...")
                },
                singleLine = true,
                maxLines = 1,
                leadingIcon = {
                    Button(onClick = { onSearch(searchValue.text) }) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search icon"
                        )
                    }
                },
                trailingIcon = {
                    Button(
                        onClick = {
                            searchValue= TextFieldValue("")
                        },
                        contentPadding = PaddingValues(0.dp),
                        enabled = searchValue.text.isNotBlank(),
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear icon",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    }
}