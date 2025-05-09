package com.vicky7230.todoapp.di.module

import android.app.Application
import android.content.Context
import com.vicky7230.todoapp.TodoApp
import com.vicky7230.todoapp.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationProvidesModule {
    @Provides
    @ApplicationContext
    internal fun provideContext(todoApp: TodoApp): Context {
        return todoApp.applicationContext
    }

    @Provides
    internal fun provideApplication(todoApp: TodoApp): Application {
        return todoApp
    }
}