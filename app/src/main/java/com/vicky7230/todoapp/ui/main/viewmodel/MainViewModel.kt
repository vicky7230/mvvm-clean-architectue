package com.vicky7230.todoapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicky7230.todoapp.domain.usecase.GetTodosUseCase
import com.vicky7230.todoapp.ui.main.state.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase
) : ViewModel() {

    private var _mainUiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Idle)
    val mainUiState: StateFlow<MainUiState> = _mainUiState.asStateFlow()

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch {
            try {
                _mainUiState.update { mainUiState: MainUiState ->
                    MainUiState.Loading
                }
                val todos = getTodosUseCase()
                _mainUiState.update { mainUiState: MainUiState ->
                    MainUiState.Todos(todos)
                }
            } catch (e: Exception) {
                Timber.e(e, "Error while fetching todos")
                _mainUiState.update { mainUiState: MainUiState ->
                    MainUiState.Error("Something went wrong")
                }
            }
        }
    }
}