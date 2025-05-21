package com.vicky7230.todoapp.ui.search.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vicky7230.todoapp.R
import com.vicky7230.todoapp.di.ViewModelFactory
import com.vicky7230.todoapp.ui.search.screen.SearchScreen
import com.vicky7230.todoapp.ui.search.viewmodel.SearchViewModel
import com.vicky7230.todoapp.ui.theme.TodoAppTheme
import dagger.android.AndroidInjection
import javax.inject.Inject

class SearchActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //setContentView(R.layout.activity_search)

        searchViewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]

        setContent {
            val searchQuery by searchViewModel.searchText.collectAsStateWithLifecycle()
            val searchResults by searchViewModel.persons.collectAsStateWithLifecycle()

            TodoAppTheme {
                SearchScreen(
                    query = searchQuery,
                    onQueryChanged = {
                        searchViewModel.onSearchTextChange(it)
                    },
                    searchResult = searchResults
                )
            }
        }
    }
}