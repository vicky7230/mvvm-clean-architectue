package com.vicky7230.todoapp.ui.main.state

import com.vicky7230.todoapp.domain.model.Product

sealed interface MainUiState {
    data object Idle : MainUiState
    data object Loading: MainUiState
    data class Error(val message: String): MainUiState
    data class Products(val products: List<Product>) : MainUiState
}

sealed interface MainSideEffect {
    data class ShowMessage(val message: String): MainSideEffect
    data class ShowError(val message: String): MainSideEffect
}