package com.example.newsdatabase.di

import android.content.Context
import com.example.feature.di.FeatureDependencies
import com.example.feature.di.viewModel.SecondViewModelModule
import com.example.newsdatabase.di.viewModel.ViewModelModule
import com.example.newsdatabase.ui.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class, NetworkModule::class, DataModule::class, DataBaseModule::class])
interface ApplicationComponent : FeatureDependencies {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(activity: MainActivity)
}
