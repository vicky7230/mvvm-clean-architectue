package com.vicky7230.todoapp.di.module

import com.vicky7230.todoapp.data.local.LocalDataSource
import com.vicky7230.todoapp.data.local.LocalDataSourceImpl
import com.vicky7230.todoapp.data.remote.RemoteDataSource
import com.vicky7230.todoapp.data.remote.RemoteDataSourceImpl
import com.vicky7230.todoapp.data.repository.ProductsRepositoryImpl
import com.vicky7230.todoapp.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class BindingModule {

    @Binds
    abstract fun bindLocalDatSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindTodoRepository(todoRepositoryImpl: ProductsRepositoryImpl): ProductsRepository
}