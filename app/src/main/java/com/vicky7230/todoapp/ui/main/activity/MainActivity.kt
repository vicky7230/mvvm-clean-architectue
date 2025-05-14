package com.vicky7230.todoapp.ui.main.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.vicky7230.todoapp.ui.main.screen.MainScreen
import com.vicky7230.todoapp.ui.main.state.MainSideEffect
import com.vicky7230.todoapp.ui.main.viewmodel.MainViewModel
import com.vicky7230.todoapp.ui.theme.TodoAppTheme
import dagger.android.AndroidInjection
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.sideEffect.collectLatest { event ->
                    when(event) {
                        is MainSideEffect.ShowError ->
                            Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT).show()
                        is MainSideEffect.ShowMessage ->
                            Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}