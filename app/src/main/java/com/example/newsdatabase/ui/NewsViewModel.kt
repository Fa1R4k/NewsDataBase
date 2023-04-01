package com.example.newsdatabase.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.NewsData
import com.example.domain.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _liveData = MutableLiveData<List<NewsData>>()
    val liveData: LiveData<List<NewsData>> get() = _liveData

    private val _progressLiveData = MutableLiveData<Boolean>()
    val progressLiveData: LiveData<Boolean> get() = _progressLiveData

    init {
        fetchData()
        observeData()
    }

    private fun fetchData() {
        compositeDisposable.add(repository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
    }

    private fun observeData() {
        _progressLiveData.value = true
        compositeDisposable.add(repository.getNewsFromDataBase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate { _progressLiveData.value = false }
            .subscribe {
                _liveData.value = it
                _progressLiveData.value = false
            })

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}