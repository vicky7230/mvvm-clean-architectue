package com.vicky7230.todoapp

import android.app.Application
import com.vicky7230.todoapp.di.component.ApplicationComponent
import com.vicky7230.todoapp.di.component.DaggerApplicationComponent
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class TodoApp : Application(), HasAndroidInjector {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        applicationComponent = DaggerApplicationComponent
            .factory()
            .create(this)

        applicationComponent.inject(this)
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

}