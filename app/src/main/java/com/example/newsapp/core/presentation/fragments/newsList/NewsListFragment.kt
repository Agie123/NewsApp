package com.example.newsapp.core.presentation.fragments.newsList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.core.data.network.model.response.Article
import com.example.newsapp.core.presentation.fragments.articleDetail.ArticleDetailsDialogFragment
import com.example.newsapp.core.presentation.viewmodel.ArticleViewModel
import com.example.newsapp.core.presentation.viewmodel.NewsViewModel
import com.example.newsapp.core.utils.OnArticleClickListener
import com.example.newsapp.core.utils.Resource
import com.example.newsapp.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment(), OnArticleClickListener {
    private val viewModel: NewsViewModel by viewModels()
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

        // Observe top headlines and update the list
        viewModel.topHeadlines.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Show loading indicator
                    Log.i("-=-loading", "onViewCreated: ")
                    binding.loadingIndicator.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.loadingIndicator.visibility = View.GONE

                    // Update RecyclerView with articles
                    val articles = resource.data.articles
                    val adapter = NewsAdapter(
                        articles = articles,
                        listener = this
                    )

                    recyclerView.adapter = adapter
                    // Set up RecyclerView adapter
                    Log.i("-=-success", "onViewCreated: " + articles)

                }

                is Resource.Error -> {
                    // Show error message
                    binding.loadingIndicator.visibility = View.GONE

                    Log.i("-=-Erroooor", "onViewCreated: ")
                }
            }
        }

        // Fetch top headlines
        viewModel.fetchTopHeadlines(country = "us")
    }

    override fun onArticleClick(article: Article) {
        val articleViewModel =
            ViewModelProvider(owner = requireActivity())[ArticleViewModel::class.java]
        articleViewModel.article.value = article

        val detailFragment = ArticleDetailsDialogFragment()
        // Use the fragment manager to navigate to the detail fragment
        detailFragment.show(parentFragmentManager, "ArticleDetailDialog")


    }
}