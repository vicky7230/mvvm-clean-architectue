package com.vicky7230.todoapp.data.remote

import com.vicky7230.todoapp.domain.model.Product
import com.vicky7230.todoapp.utils.ApiHandle.handleApi
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {
    override suspend fun getProducts(): NetworkResult<List<Product>> {
        return handleApi { apiService.getProducts() }
    }
}