package com.example.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.NewsData
import com.example.domain.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _liveData = MutableLiveData<List<NewsData>>()
    val liveData: LiveData<List<NewsData>> get() = _liveData

    private val publishSubject = PublishSubject.create<String>()

    fun observeSearch() {
        compositeDisposable.add(
            publishSubject
                .delay(500, TimeUnit.MILLISECONDS)
                .switchMap {
                    repository.search(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _liveData.value = it
                })
    }

    fun search(query: String) {
        publishSubject.onNext(query)
    }
}

