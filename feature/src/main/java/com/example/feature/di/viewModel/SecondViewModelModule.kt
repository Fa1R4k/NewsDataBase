package com.example.feature.di.viewModel

import androidx.lifecycle.ViewModel
import com.example.core.ViewModelKey
import com.example.feature.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SecondViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindLoginViewModel(viewModel: SearchViewModel): ViewModel
}
