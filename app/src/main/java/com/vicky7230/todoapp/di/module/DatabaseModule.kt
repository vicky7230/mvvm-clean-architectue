package com.vicky7230.todoapp.di.module

import android.content.Context
import androidx.room.Room
import com.vicky7230.todoapp.data.local.ProductsDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideProductsDb(context: Context): ProductsDb {
        return Room.databaseBuilder(context, ProductsDb::class.java, "ProductsDb").build()
    }
}