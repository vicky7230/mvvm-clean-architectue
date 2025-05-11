package com.vicky7230.todoapp.domain.repository

import com.vicky7230.todoapp.data.remote.NetworkResult
import com.vicky7230.todoapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(): NetworkResult<List<Product>>
    suspend fun saveProducts(products: List<Product>)
    fun getProductsFromDb(): Flow<List<Product>>
}