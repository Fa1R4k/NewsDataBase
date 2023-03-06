package com.example.newsdatabase.data.database

import androidx.room.*

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getAll(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertALl(users: List<NewsEntity>)

    @Delete
    fun delete(user: NewsEntity)

}