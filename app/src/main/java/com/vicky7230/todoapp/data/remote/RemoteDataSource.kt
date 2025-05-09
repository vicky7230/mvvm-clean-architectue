package com.vicky7230.todoapp.data.remote

import com.vicky7230.todoapp.domain.model.TodoDto

interface RemoteDataSource {
    suspend fun getTodos(): List<TodoDto>
}