package com.vicky7230.todoapp.utils

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String
)

enum class LOGIN_STATUS {
    INVALID_USER,
    INVALID_PASSWORD,
    UNKNOWN_USER,
    SUCCESS
}