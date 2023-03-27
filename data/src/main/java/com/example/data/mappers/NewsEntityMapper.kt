package com.example.data.mappers

import com.example.data.database.NewsEntity
import com.example.data.models.NewsResponse
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