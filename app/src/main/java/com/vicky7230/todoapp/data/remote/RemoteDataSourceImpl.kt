package com.vicky7230.todoapp.data.remote

import com.vicky7230.todoapp.domain.model.TodoDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {
    override suspend fun getTodos(): List<TodoDto> {
        return apiService.getTodos()
    }
}