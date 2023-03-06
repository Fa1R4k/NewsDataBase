package com.example.newsdatabase.ui

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.domain.NewsData
import com.example.newsdatabase.R


class NewsViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {
    fun onBind(item: NewsData) {
        val image = itemView.findViewById<ImageView>(R.id.ivNewsImage)
        val name = itemView.findViewById<TextView>(R.id.tvTitleNews)
        val rating = itemView.findViewById<TextView>(R.id.tvNewsAuthor)

        Glide.with(image).load(item.imageLink).into(image)
        name.text = item.title
        rating.text = item.author
        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(item.url)
            name.context.startActivity(intent)
        }
    }
}
