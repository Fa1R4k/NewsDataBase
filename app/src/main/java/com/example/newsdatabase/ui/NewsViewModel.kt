package com.example.newsdatabase.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.NewsData
import com.example.newsapp.domain.NewsRepository
import com.example.newsdatabase.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {

    private val _liveData = MutableLiveData<List<NewsData>>()
    val liveData: LiveData<List<NewsData>> get() = _liveData

    private val _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> get() = _errorLiveData

    private val _progressLiveData = MutableLiveData<Boolean>()
    val progressLiveData: LiveData<Boolean> get() = _progressLiveData

    private val exception = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is SocketTimeoutException -> _errorLiveData.value = 0
            else -> _errorLiveData.value = 1
        }
    }

    fun getNews() {
        _progressLiveData.value = true
        viewModelScope.launch(exception) {
            _liveData.value = repository.getListNewsData()
            _progressLiveData.value = false
        }
    }
}