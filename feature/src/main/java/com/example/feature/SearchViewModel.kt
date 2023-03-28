package com.example.feature

import android.app.appsearch.SearchResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.NewsData
import com.example.domain.NewsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {

    companion object {
        private const val NOTHING : String = "NOTHING"
    }

    private val trigger = MutableStateFlow("")

    fun setQuery(query: String) {
        trigger.value = query
    }

    val results: Flow<List<NewsData>> = trigger.mapLatest { query ->
        if (query.isNotEmpty()) repository.search(query) else repository.search(NOTHING)
    }.stateIn(scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList())
}

