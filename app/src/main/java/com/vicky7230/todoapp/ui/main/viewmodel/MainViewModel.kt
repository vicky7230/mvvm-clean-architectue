package com.vicky7230.todoapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicky7230.todoapp.data.remote.NetworkResult
import com.vicky7230.todoapp.domain.usecase.GetLocalProductsUseCase
import com.vicky7230.todoapp.domain.usecase.GetRemoteProductsUseCase
import com.vicky7230.todoapp.domain.usecase.SaveProductsUseCase
import com.vicky7230.todoapp.ui.main.state.MainSideEffect
import com.vicky7230.todoapp.ui.main.state.MainUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getRemoteProductsUseCase: GetRemoteProductsUseCase,
    private val getLocalProductsUseCase: GetLocalProductsUseCase,
    private val saveProductsUseCase: SaveProductsUseCase
) : ViewModel() {

    private var _mainUiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Idle)
    val mainUiState: StateFlow<MainUiState> = _mainUiState.asStateFlow()

    private var _sideEffect = MutableSharedFlow<MainSideEffect>()
    val sideEffect: SharedFlow<MainSideEffect> = _sideEffect.asSharedFlow()

    init {
        //observe the data from local
        viewModelScope.launch {
            getLocalProductsUseCase()
                .distinctUntilChanged()
                .collectLatest {
                _mainUiState.update { currentState: MainUiState ->
                    MainUiState.Products(it)
                }
            }
        }

        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _mainUiState.update { currentState: MainUiState ->
                MainUiState.Loading
            }
            delay(2000) // just for testing
            when (val response = getRemoteProductsUseCase()) {
                is NetworkResult.Success -> {
                    saveProductsUseCase(response.data)
                    _sideEffect.emit(MainSideEffect.ShowError("Successful"))
                }

                is NetworkResult.Error -> {
                    _mainUiState.update { currentState: MainUiState ->
                        MainUiState.Error("${response.code}, ${response.message} ")
                    }

                    _sideEffect.emit(MainSideEffect.ShowError("Something went wrong"))
                }

                is NetworkResult.Exception -> {
                    _mainUiState.update { currentState: MainUiState ->
                        MainUiState.Error("${response.e.message}")
                    }
                    _sideEffect.emit(MainSideEffect.ShowError("Something went wrong"))
                }
            }

        }
    }
}