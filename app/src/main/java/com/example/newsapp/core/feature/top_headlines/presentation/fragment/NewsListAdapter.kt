package com.example.newsapp.core.feature.top_headlines.presentation.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.core.feature.article_details.domain.Article
import com.example.newsapp.core.utils.OnArticleClickListener

class NewsListAdapter(
    private var articles: List<Article>,
    private val listener: OnArticleClickListener
) : RecyclerView.Adapter<NewsListAdapter.NewsArticleViewHolder>() {

    inner class NewsArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val imageView: ImageView = view.findViewById(R.id.imageView)

        init {
            view.setOnClickListener {
                listener.onArticleClick(article = articles[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)

        return NewsArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        val article = articles[position]

        holder.titleTextView.text = article.title
        holder.descriptionTextView.text = article.description

        // Load the image using an image loading library (e.g., Glide)
        Glide.with(holder.itemView.context)
            .load(article.urlToImage)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun updateArticles(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}