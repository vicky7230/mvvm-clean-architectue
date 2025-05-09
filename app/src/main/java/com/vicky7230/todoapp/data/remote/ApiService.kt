package com.vicky7230.todoapp.data.remote

import com.vicky7230.todoapp.domain.model.TodoDto
import retrofit2.http.GET

interface ApiService {
    @GET("todos")
    suspend fun getTodos():List<TodoDto>
}