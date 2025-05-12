package com.vicky7230.todoapp.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.vicky7230.todoapp.data.local.dao.ProductDao
import com.vicky7230.todoapp.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    autoMigrations = [AutoMigration(from = 1, to = 2)],
    version = 2,
    exportSchema = true)
abstract class ProductsDb: RoomDatabase() {

    abstract fun getProductsDao(): ProductDao
}