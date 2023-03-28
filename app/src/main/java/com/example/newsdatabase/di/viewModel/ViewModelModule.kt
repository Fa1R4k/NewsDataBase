package com.example.newsdatabase.di.viewModel

import androidx.lifecycle.ViewModel
import com.example.core.ViewModelKey
import com.example.newsdatabase.ui.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    fun bindLoginViewModel(viewModel: NewsViewModel): ViewModel
}
