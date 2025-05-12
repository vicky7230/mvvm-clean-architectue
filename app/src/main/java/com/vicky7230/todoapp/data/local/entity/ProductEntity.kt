package com.vicky7230.todoapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    @Embedded
    val rating: RatingEntity
)

data class RatingEntity(
    @ColumnInfo(defaultValue = "0.0")
    val rate: Double,
    @ColumnInfo(defaultValue = "0.0")
    val count: Int
)