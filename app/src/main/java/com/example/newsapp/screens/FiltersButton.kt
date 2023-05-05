package com.example.newsapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun FilterButton(
    filters: List<Filter>,
    onFilterSelected: (Filter) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true },
        modifier = Modifier.size(36.dp)
    ) {
        Icon(
            imageVector =  Icons.Filled.BookmarkAdd,
            contentDescription = "Filter icon"
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        filters.forEach { filter ->
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onFilterSelected(filter)
                }
            ) {
                Text(
                    text = filter.name,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}