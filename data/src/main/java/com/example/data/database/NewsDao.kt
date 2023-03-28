package com.example.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getAll(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news_table")
    fun getAllWithoutFlow(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertALl(users: List<NewsEntity>)

    @Delete
    fun delete(users: List<NewsEntity>)

}