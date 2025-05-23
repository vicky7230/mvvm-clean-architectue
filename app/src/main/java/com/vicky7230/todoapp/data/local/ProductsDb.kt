package com.vicky7230.todoapp.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.vicky7230.todoapp.data.local.dao.ProductDao
import com.vicky7230.todoapp.data.local.dao.QuotesDao
import com.vicky7230.todoapp.data.local.entity.ProductEntity
import com.vicky7230.todoapp.data.local.entity.QuoteEntity

@Database(
    entities = [ProductEntity::class, QuoteEntity::class],
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
    ],
    version = 3,
    exportSchema = true
)
abstract class ProductsDb : RoomDatabase() {

    abstract fun getProductsDao(): ProductDao

    abstract fun getQuotesDao(): QuotesDao
}