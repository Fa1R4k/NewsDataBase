package com.example.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ViewModelFactory
import com.example.core.findDependencies
import com.example.feature.di.DaggerFeatureComponent
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
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
        setupMenuRecyclerView()
        observeFlow()

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (start != 0) {
                    viewModel.setQuery(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        etFoundString.addTextChangedListener(textWatcher)

    }

    private fun bindView() {
        etFoundString = findViewById<EditText>(R.id.etFoundString)
        rvFoundNews = findViewById<RecyclerView>(R.id.rvFoundNews)
    }

    private fun observeFlow(){
        this@SearchActivity.lifecycleScope.launch {
            viewModel.results
                .flowWithLifecycle(
                    this@SearchActivity.lifecycle,
                    Lifecycle.State.STARTED
                )
                .distinctUntilChanged()
                .collect {
                    adapter.setItems(it)
                }
        }
    }

    private fun setupMenuRecyclerView() {
        rvFoundNews.adapter = adapter
        rvFoundNews.layoutManager = LinearLayoutManager(
            this@SearchActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}