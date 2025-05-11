package com.vicky7230.todoapp.domain.usecase

import com.vicky7230.todoapp.domain.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        productsRepository.getProducts()
    }
}