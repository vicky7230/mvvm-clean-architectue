package com.vicky7230.todoapp.domain.usecase

import com.vicky7230.todoapp.domain.model.Product
import com.vicky7230.todoapp.domain.repository.ProductsRepository
import javax.inject.Inject

class SaveProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(products: List<Product>) {
        productsRepository.saveProducts(products)
    }
}