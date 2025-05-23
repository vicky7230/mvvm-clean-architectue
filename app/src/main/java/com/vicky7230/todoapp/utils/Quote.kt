package com.vicky7230.todoapp.utils

import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val text: String,
    val author: String
)
