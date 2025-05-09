package com.vicky7230.todoapp.data.repository

import com.vicky7230.todoapp.data.remote.RemoteDataSource
import com.vicky7230.todoapp.domain.model.TodoDto
import com.vicky7230.todoapp.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): TodoRepository {
    override suspend fun getTodos(): List<TodoDto> {
       return remoteDataSource.getTodos()
    }
}