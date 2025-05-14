package com.vicky7230.todoapp.ui.main.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vicky7230.todoapp.domain.model.Product
import com.vicky7230.todoapp.domain.model.Rating
import com.vicky7230.todoapp.ui.main.state.MainUiState

class MainUiPreviewProvider : PreviewParameterProvider<MainUiState> {
    override val values = sequenceOf(
        //MainUiState.Idle,
        //MainUiState.Loading,
        //MainUiState.Error(message = "something went wrong"),
        MainUiState.Products(
            listOf(
                Product(
                    id = 1,
                    title = "First Product",
                    price = 10.0,
                    description = "Mens Casual Premium Slim Fit T-Shirts \",\"price\":22.3,\"description\":\"Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.",
                    category = "Category A",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                    rating = Rating(
                        rate = 4.0,
                        count = 50
                    )
                ),
                Product(
                    id = 2,
                    title = "Second Item",
                    price = 25.50,
                    description = "Details about the second item.",
                    category = "Category B",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                    rating = Rating(
                        rate = 3.5,
                        count = 120
                    )
                ),
                Product(
                    id = 3,
                    title = "Third Thing",
                    price = 5.99,
                    description = "A brief description of the third thing.",
                    category = "Category A",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                    rating = Rating(
                        rate = 4.8,
                        count = 200
                    )
                ),
                Product(
                    id = 4,
                    title = "Number Four",
                    price = 100.00,
                    description = "Elaborate description for number four.",
                    category = "Category C",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                    rating = Rating(
                        rate = 2.9,
                        count = 75
                    )
                ),
                Product(
                    id = 5,
                    title = "The Last One",
                    price = 15.75,
                    description = "Final product in this list.",
                    category = "Category B",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                    rating = Rating(
                        rate = 4.2,
                        count = 150
                    )
                )
            )
        )
    )
}