package com.example.newsapp.core.feature.top_headlines.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.core.feature.article_details.domain.Article
import com.example.newsapp.core.feature.article_details.presentation.fragment.ArticleDetailsDialogFragment
import com.example.newsapp.core.feature.article_details.presentation.viewmodel.ArticleDetailsViewModel
import com.example.newsapp.core.feature.top_headlines.presentation.viewmodel.TopHeadLinesViewModel
import com.example.newsapp.core.utils.OnArticleClickListener
import com.example.newsapp.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment(), OnArticleClickListener {
    private val topHeadLinesViewModel: TopHeadLinesViewModel by activityViewModels()
    private val articleDetailsViewModel: ArticleDetailsViewModel by activityViewModels()
    private lateinit var binding: FragmentNewsListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = binding.newsListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = NewsListAdapter(
            articles = emptyList(),
            listener = this
        )
        recyclerView.adapter = adapter

        topHeadLinesViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.loadingIndicator.visibility = View.VISIBLE
            } else {
                binding.loadingIndicator.visibility = View.GONE
            }
        }

        topHeadLinesViewModel.topHeadlines.observe(viewLifecycleOwner) { result ->
            val articles = result.articles
            adapter.updateArticles(newArticles = articles)
        }

        // Fetch top headlines
        topHeadLinesViewModel.fetchTopHeadlines()
    }

    override fun onArticleClick(article: Article) {
        articleDetailsViewModel.currentArticle.value = article

        ArticleDetailsDialogFragment().show(parentFragmentManager, "ArticleDetailDialog")
    }
}