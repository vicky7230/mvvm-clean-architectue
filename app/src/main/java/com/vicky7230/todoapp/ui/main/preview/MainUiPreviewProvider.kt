package com.vicky7230.todoapp.ui.main.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.vicky7230.todoapp.ui.main.state.MainUiState

class MainUiPreviewProvider: PreviewParameterProvider<MainUiState> {
    override val values = sequenceOf(
        MainUiState.Idle,
        MainUiState.Loading,
        MainUiState.Error(message = "something went wrong"),
        MainUiState.Todos(emptyList())
    )
}