package com.example.newsdatabase.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.AppDataBase
import com.example.data.database.NewsDao
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    @Provides
    fun provideDataBase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "database-name")
            .build()
    }

    @Provides
    fun provideUserDao(db: AppDataBase): NewsDao = db.getNewsDao()
}