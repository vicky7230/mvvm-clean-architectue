package com.vicky7230.todoapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.vicky7230.todoapp.di.ViewModelFactory
import com.vicky7230.todoapp.di.ViewModelKey
import com.vicky7230.todoapp.ui.main.viewmodel.MainViewModel
import com.vicky7230.todoapp.ui.search.viewmodel.SearchViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

}