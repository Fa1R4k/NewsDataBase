package com.example.feature

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ViewModelFactory
import com.example.core.findDependencies
import com.example.feature.di.DaggerFeatureComponent
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: SearchViewModel by viewModels { factory }
    private lateinit var etFoundString: EditText
    private lateinit var rvFoundNews: RecyclerView
    private var adapter = SearchNewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFeatureComponent.factory().create(findDependencies()).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        bindView()
        viewModel.observeSearch()
        setupMenuRecyclerView()
        observeLiveData()

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (start != 0) {
                    viewModel.search(s.toString())
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        }
        etFoundString.addTextChangedListener(textWatcher)
    }

    private fun bindView() {
        etFoundString = findViewById(R.id.etFoundString)
        rvFoundNews = findViewById(R.id.rvFoundNews)
    }

    private fun observeLiveData() {
        viewModel.liveData.observe(this) {
            adapter.setItems(it)
        }
    }

    private fun setupMenuRecyclerView() {
        rvFoundNews.adapter = adapter
        rvFoundNews.layoutManager =
            LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)
    }
}