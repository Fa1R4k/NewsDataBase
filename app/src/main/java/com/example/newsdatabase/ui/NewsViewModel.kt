package com.example.newsdatabase.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.NewsData
import com.example.domain.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {

    private val _liveData = MutableLiveData<List<NewsData>>()
    val liveData: LiveData<List<NewsData>> get() = _liveData

    private val _progressLiveData = MutableLiveData<Boolean>()
    val progressLiveData: LiveData<Boolean> get() = _progressLiveData

    init {
        fetchData()
        observeData()
    }

    private fun fetchData(){
        viewModelScope.launch {
            repository.getNews()
        }
    }

    private fun observeData() {
        _progressLiveData.value = true
        viewModelScope.launch {
            repository.getNewsFromDataBase().collect{
                _liveData.value = it
                _progressLiveData.value = false
            }
        }
    }
}