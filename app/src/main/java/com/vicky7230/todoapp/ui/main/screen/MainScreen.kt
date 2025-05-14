package com.vicky7230.todoapp.ui.main.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.vicky7230.todoapp.R
import com.vicky7230.todoapp.domain.model.Product
import com.vicky7230.todoapp.ui.main.composables.AppToolBar
import com.vicky7230.todoapp.ui.main.composables.ErrorUi
import com.vicky7230.todoapp.ui.main.composables.LoadingUi
import com.vicky7230.todoapp.ui.main.preview.MainUiPreviewProvider
import com.vicky7230.todoapp.ui.main.state.MainUiState
import com.vicky7230.todoapp.ui.theme.TodoAppTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    state: MainUiState
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppToolBar(title = "Home")
        }
    ) { innerPadding ->
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

            is MainUiState.Products -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    state.products.forEach { product ->
                        ProductView(
                            modifier = Modifier.fillMaxWidth(),
                            product = product
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductView(
    modifier: Modifier = Modifier,
    product: Product
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.image)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_background),
            error = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Product Image",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(120.dp)
                .padding(start = 10.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = product.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
            Text(
                text = product.description,
                fontSize = 16.sp,
                color = Color.Gray,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Rating: ${product.rating.rate}",
                fontSize = 16.sp,
                color = Color.Blue
            )
            Text(
                text = "Quantity ${product.rating.count}"
            )
        }
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