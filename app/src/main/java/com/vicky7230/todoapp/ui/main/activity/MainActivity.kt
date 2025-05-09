package com.vicky7230.todoapp.ui.main.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vicky7230.todoapp.ui.main.screen.MainScreen
import com.vicky7230.todoapp.ui.main.viewmodel.MainViewModel
import com.vicky7230.todoapp.ui.theme.TodoAppTheme
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        setContent {
            val state = mainViewModel.mainUiState.collectAsStateWithLifecycle()
            TodoAppTheme {
                MainScreen(
                    modifier = Modifier.fillMaxSize(),
                    state = state.value
                )
            }
        }
    }
}