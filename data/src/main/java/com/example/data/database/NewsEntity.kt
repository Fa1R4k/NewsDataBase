package com.example.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NewsEntity(
    @ColumnInfo val title: String,
    @ColumnInfo val imageLink: String,
    @PrimaryKey val url: String,
    @ColumnInfo val author: String,
)
