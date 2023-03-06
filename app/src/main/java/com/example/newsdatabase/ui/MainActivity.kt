package com.example.newsdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsdatabase.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<NewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var adapter: NewsAdapter
        val recycler = findViewById<RecyclerView>(R.id.rv)
        viewModel.liveData.observe(this) {
            adapter = NewsAdapter(it)
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        viewModel.progressLiveData.observe(this) {
            progressBar.isVisible = it
        }
        viewModel.errorLiveData.observe(this) {
            Toast.makeText(this, getString(it), Toast.LENGTH_SHORT).show()
        }
        viewModel.getNews()
    }
}