package com.vicky7230.todoapp.data.remote

import com.vicky7230.todoapp.domain.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>
}