package com.vicky7230.todoapp.di.component

import android.content.Context
import com.vicky7230.todoapp.TodoApp
import com.vicky7230.todoapp.di.module.ActivityBindingModule
import com.vicky7230.todoapp.di.module.BindingModule
import com.vicky7230.todoapp.di.module.ApplicationModule
import com.vicky7230.todoapp.di.module.DatabaseModule
import com.vicky7230.todoapp.di.module.NetworkModule
import com.vicky7230.todoapp.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        BindingModule::class,
        ApplicationModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(todoApp: TodoApp)
}