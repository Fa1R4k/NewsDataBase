package com.example.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.NewsData

class SearchNewsAdapter() :
    RecyclerView.Adapter<SearchNewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_news, parent, false)
        return SearchNewsViewHolder(view)
    }

    private val listNewsData: MutableList<NewsData> = mutableListOf()

    override fun getItemCount(): Int = listNewsData.size

    override fun onBindViewHolder(holder: SearchNewsViewHolder, position: Int) {
        holder.onBind(listNewsData[position])
    }

    fun setItems(newList: List<NewsData>) {
        listNewsData.clear()
        listNewsData.addAll(newList)
        notifyDataSetChanged()
    }
}