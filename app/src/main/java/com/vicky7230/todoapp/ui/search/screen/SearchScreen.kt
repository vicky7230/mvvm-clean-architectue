package com.vicky7230.todoapp.ui.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vicky7230.todoapp.ui.search.viewmodel.Person
import com.vicky7230.todoapp.ui.theme.TodoAppTheme

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChanged: (String) -> Unit,
    searchResult: List<Person>
) {
    Scaffold { it: PaddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TextField(
                value = query,
                onValueChange = onQueryChanged,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                },
                placeholder = { Text(text = "Search...") },
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {
                items(searchResult) { item ->
                    Text(
                        text = "${item.firstName} ${item.lastName}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewSearchScreen() {
    TodoAppTheme {
        SearchScreen(
            query = "",
            onQueryChanged = {},
            searchResult = listOf(
                Person("Vipin", "Kumar"),
                Person("Vipin", "Kumar"),
                Person("Vipin", "Kumar"),
                Person("Vipin", "Kumar")
            )
        )
    }
}