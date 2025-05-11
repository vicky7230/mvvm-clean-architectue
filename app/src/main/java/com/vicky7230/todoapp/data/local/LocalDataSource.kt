package com.vicky7230.todoapp.data.local

import com.vicky7230.todoapp.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun addProducts(products: List<ProductEntity>)

    fun getProducts(): Flow<List<ProductEntity>>
}