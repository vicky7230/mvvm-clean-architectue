package com.vicky7230.todoapp.data.remote

import com.vicky7230.todoapp.domain.model.Product

interface RemoteDataSource {
    suspend fun getProducts(): NetworkResult<List<Product>>
}