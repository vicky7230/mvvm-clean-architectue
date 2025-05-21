package com.vicky7230.todoapp.ui.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _persons = MutableStateFlow(listOf<Person>())
    val persons = _persons.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    init {
        instantSearch()
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun instantSearch() {
        viewModelScope.launch {
            _searchText
                .debounce(300)
                .filter { query: String ->
                    query.isNotEmpty() && query.length > 2
                }
                .distinctUntilChanged()
                .flatMapLatest { query: String ->
                    dataFromNetwork(query = query)
                        .catch {
                            emit(emptyList())
                        }
                }
                .flowOn(Dispatchers.Default)
                .collect {
                    _persons.value = it
                }
        }
    }

    private fun dataFromNetwork(query: String): Flow<List<Person>> {
        return flow {
            delay(1500)
            val data: List<Person> = allPersons.filter { person: Person ->
                person.firstName.contains(query, ignoreCase = true) ||
                        person.lastName.contains(query, ignoreCase = true)
            }
            emit(data)
        }
    }

}

data class Person(
    val firstName: String,
    val lastName: String
)

private val allPersons = listOf(
    Person(
        firstName = "Vipin",
        lastName = "Kumar"
    ),
    Person(
        firstName = "Deepak",
        lastName = "Kamboj"
    ),
    Person(
        firstName = "Nitesh",
        lastName = "Singh"
    ),
    Person(
        firstName = "Rahul",
        lastName = "Yadav"
    ),
    Person(
        firstName = "Bharat",
        lastName = "Singh"
    ),
    Person(
        firstName = "Ajay",
        lastName = "Kumar"
    ),
)