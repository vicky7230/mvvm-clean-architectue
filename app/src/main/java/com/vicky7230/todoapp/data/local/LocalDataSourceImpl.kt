package com.vicky7230.todoapp.data.local

import com.vicky7230.todoapp.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val productsDb: ProductsDb
) : LocalDataSource {
    override suspend fun addProducts(products: List<ProductEntity>) {
        productsDb.getProductsDao().addProducts(products)
    }

    override fun getProducts(): Flow<List<ProductEntity>> {
        return productsDb.getProductsDao().getProducts()
    }
}