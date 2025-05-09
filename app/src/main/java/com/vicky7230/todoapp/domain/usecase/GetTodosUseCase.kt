package com.vicky7230.todoapp.domain.usecase

import com.vicky7230.todoapp.domain.model.TodoDto
import com.vicky7230.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {

    suspend operator fun invoke(): List<TodoDto> = withContext(Dispatchers.IO) {
        todoRepository.getTodos()
    }
}