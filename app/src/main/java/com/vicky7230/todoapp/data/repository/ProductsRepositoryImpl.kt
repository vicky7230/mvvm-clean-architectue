package com.vicky7230.todoapp.data.repository

import com.vicky7230.todoapp.data.local.LocalDataSource
import com.vicky7230.todoapp.data.local.entity.ProductEntity
import com.vicky7230.todoapp.data.local.mappers.toDomain
import com.vicky7230.todoapp.data.local.mappers.toEntity
import com.vicky7230.todoapp.data.remote.NetworkResult
import com.vicky7230.todoapp.data.remote.RemoteDataSource
import com.vicky7230.todoapp.domain.model.Product
import com.vicky7230.todoapp.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ProductsRepository {
    override suspend fun getProducts(): NetworkResult<List<Product>> {
        return remoteDataSource.getProducts()
    }

    override suspend fun saveProducts(products: List<Product>) {
        return localDataSource.addProducts(products.map { it.toEntity() })
    }

    override fun getProductsFromDb(): Flow<List<Product>> {
        return localDataSource.getProducts()
            .map { list: List<ProductEntity> -> list.map { it.toDomain() } }
    }
}