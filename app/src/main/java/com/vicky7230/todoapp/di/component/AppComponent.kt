package com.vicky7230.todoapp.di.component

import com.vicky7230.todoapp.TodoApp
import com.vicky7230.todoapp.di.module.ActivityBindingModule
import com.vicky7230.todoapp.di.module.ApplicationBindsModule
import com.vicky7230.todoapp.di.module.ApplicationProvidesModule
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
        ApplicationBindsModule::class,
        ApplicationProvidesModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(todoApp: TodoApp): Builder

        fun build(): ApplicationComponent
    }

    fun inject(todoApp: TodoApp)
}