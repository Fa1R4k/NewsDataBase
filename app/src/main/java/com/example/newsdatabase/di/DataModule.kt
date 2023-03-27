package com.example.newsdatabase.di

import com.example.data.NewsRepositoryImpl
import com.example.domain.NewsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {
    @Binds
    abstract fun getRepository(impl: NewsRepositoryImpl): NewsRepository
}