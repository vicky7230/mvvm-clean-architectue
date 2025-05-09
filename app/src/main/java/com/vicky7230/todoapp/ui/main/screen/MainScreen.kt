package com.vicky7230.todoapp.ui.main.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vicky7230.todoapp.ui.main.preview.MainUiPreviewProvider
import com.vicky7230.todoapp.ui.main.state.MainUiState
import com.vicky7230.todoapp.ui.theme.TodoAppTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    state: MainUiState
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when (state) {
            is MainUiState.Error -> ErrorUi(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                message = state.message
            )

            MainUiState.Idle -> Unit
            MainUiState.Loading -> LoadingUi(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )

            is MainUiState.Todos -> {
                // TODO
            }
        }
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

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4
)
@Composable
fun MainScreenPreview(@PreviewParameter(MainUiPreviewProvider::class) state: MainUiState) {
    TodoAppTheme {
        MainScreen(
            state = state,
            modifier = Modifier.fillMaxSize()
        )
    }
}