package com.vicky7230.todoapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TodoDto(
    val userId: String,
    val id: Int,
    val title: String,
    val completed: Boolean
)