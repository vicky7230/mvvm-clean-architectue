package com.vicky7230.todoapp.data.local.mappers

import com.vicky7230.todoapp.data.local.entity.ProductEntity
import com.vicky7230.todoapp.data.local.entity.RatingEntity
import com.vicky7230.todoapp.domain.model.Product
import com.vicky7230.todoapp.domain.model.Rating

fun ProductEntity.toDomain(): Product {
    return Product(
        id = this.id,
        title = this.title,
        price = this.price,
        description = this.description,
        category = this.category,
        image = this.image,
        rating = Rating(
            rate = this.rating.rate,
            count = this.rating.count
        )
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        title = this.title,
        price = this.price,
        description = this.description,
        category = this.category,
        image = this.image,
        rating = RatingEntity(
            rate = this.rating.rate,
            count = this.rating.count
        )
    )
}