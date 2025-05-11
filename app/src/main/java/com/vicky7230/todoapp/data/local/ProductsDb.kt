package com.vicky7230.todoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vicky7230.todoapp.data.local.dao.ProductDao
import com.vicky7230.todoapp.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = true)
abstract class ProductsDb: RoomDatabase() {

    abstract fun getProductsDao(): ProductDao
}