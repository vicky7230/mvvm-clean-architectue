package com.vicky7230.todoapp.ui.main.state

import com.vicky7230.todoapp.domain.model.TodoDto

sealed interface MainUiState {
    data object Idle : MainUiState
    data object Loading: MainUiState
    data class Error(val message: String): MainUiState
    data class Todos(val todos: List<TodoDto>) : MainUiState
}
