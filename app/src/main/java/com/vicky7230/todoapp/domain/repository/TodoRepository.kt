package com.vicky7230.todoapp.domain.repository

import com.vicky7230.todoapp.domain.model.TodoDto

interface TodoRepository {
    suspend fun getTodos(): List<TodoDto>
}