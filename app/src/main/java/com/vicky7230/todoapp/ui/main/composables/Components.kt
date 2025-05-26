package com.vicky7230.todoapp.ui.main.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vicky7230.todoapp.ui.theme.TodoAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(
    modifier: Modifier = Modifier,
    title: String,
    onSearchClick: () -> Unit,
    onDataClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    color = Color.White
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            Icon(
                modifier = Modifier,
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu",
                tint = Color.White
            )
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }
            IconButton(onClick = onDataClick) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Data",
                    tint = Color.White
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewAppToolBar() {
    TodoAppTheme {
        AppToolBar(title = "Home", onSearchClick = {}, onDataClick = {})
    }
}


@Composable
fun ErrorUi(
    modifier: Modifier = Modifier,
    message: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberVectorPainter(image = Icons.Filled.Warning),
            contentDescription = "Warning icon",
            modifier = Modifier.size(140.dp),
            colorFilter = ColorFilter.tint(Color.Red)
        )
        Text(
            text = message,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun LoadingUi(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(84.dp),
            color = Color.Red,
            strokeWidth = 8.dp
        )
    }
}