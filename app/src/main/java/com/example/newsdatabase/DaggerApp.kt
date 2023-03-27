package com.example.newsdatabase

import android.app.Application
import com.example.core.HasDependencies
import com.example.newsdatabase.di.ApplicationComponent
import com.example.newsdatabase.di.DaggerApplicationComponent

class DaggerApp : Application(), HasDependencies {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
    override val dependencies: Any
        get() = appComponent
}