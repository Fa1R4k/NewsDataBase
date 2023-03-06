package com.example.newsdatabase.data.mappers

import com.example.newsapp.data.models.NewsResponse
import com.example.newsdatabase.data.database.NewsEntity
import javax.inject.Inject

class NewsEntityMapper @Inject constructor() {
    operator fun invoke(response: List<NewsResponse>): List<NewsEntity> = with(response){
        return response.map {
            NewsEntity(
                title = it.title.orEmpty(),
                imageLink = it.imageLink.orEmpty(),
                url = it.url.orEmpty(),
                author = it.author.orEmpty()
            )
        }
    }
}