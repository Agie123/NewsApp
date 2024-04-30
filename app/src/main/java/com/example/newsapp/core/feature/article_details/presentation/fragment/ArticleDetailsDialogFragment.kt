package com.example.newsapp.core.feature.article_details.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.core.feature.article_details.presentation.viewmodel.ArticleDetailsViewModel
import com.example.newsapp.core.utils.DateFormatter
import com.example.newsapp.databinding.FragmentArticleDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentArticleDetailBinding
    private val articleDetailsViewModel: ArticleDetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(
            "ArticleDetailsDialog",
            "NewsViewModel instance: ${articleDetailsViewModel.hashCode()}"
        )


        articleDetailsViewModel.currentArticle.observe(viewLifecycleOwner) { currentArticle ->
            binding.titleTextView.text = currentArticle.title
            binding.publishedAtTextView.text = getString(
                R.string.published_at,
                DateFormatter.formatDateTime(dateTime = currentArticle.publishedAt)
            )

            binding.authorTextView.apply {
                if (currentArticle.author.isNullOrEmpty()) visibility = View.GONE else text =
                    getString(R.string.by_author, currentArticle.author)
            }

            binding.contentTextView.apply {
                if (currentArticle.content.isNullOrEmpty()) visibility = View.GONE else text =
                    currentArticle.content
            }

            currentArticle.urlToImage?.let {
                Glide.with(binding.imageView.context)
                    .load(it)
                    .into(binding.imageView)
            } ?: run {
                binding.imageView.visibility = View.GONE
            }

            binding.closeButton.setOnClickListener {
                dismiss()
            }
        }
    }
}