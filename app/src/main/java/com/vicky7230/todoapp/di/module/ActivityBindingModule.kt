package com.vicky7230.todoapp.di.module

import com.vicky7230.todoapp.ui.main.activity.MainActivity
import com.vicky7230.todoapp.ui.main.activity.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSearchActivity(): SearchActivity
}