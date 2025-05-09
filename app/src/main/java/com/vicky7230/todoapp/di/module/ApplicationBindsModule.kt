package com.vicky7230.todoapp.di.module

import com.vicky7230.todoapp.data.remote.RemoteDataSource
import com.vicky7230.todoapp.data.remote.RemoteDataSourceImpl
import com.vicky7230.todoapp.data.repository.TodoRepositoryImpl
import com.vicky7230.todoapp.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationBindsModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindTodoRepository(todoRepositoryImpl: TodoRepositoryImpl): TodoRepository
}