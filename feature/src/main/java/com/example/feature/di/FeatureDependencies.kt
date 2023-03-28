package com.example.feature.di

import com.example.domain.NewsRepository

interface FeatureDependencies {

    fun repository(): NewsRepository
}