package com.example.newsdatabase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ViewModelFactory
import com.example.feature.SearchActivity
import com.example.newsdatabase.DaggerApp
import com.example.newsdatabase.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: NewsViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as DaggerApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycler = findViewById<RecyclerView>(R.id.rv)
        viewModel.liveData.observe(this) {
            recycler.adapter = NewsAdapter(it)
            recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        viewModel.progressLiveData.observe(this) {
            progressBar.isVisible = it
        }

        findViewById<ImageView>(R.id.imageView).setOnClickListener {
            val intent = Intent(this@MainActivity, SearchActivity::class.java)
            startActivity(intent)
        }
    }
}