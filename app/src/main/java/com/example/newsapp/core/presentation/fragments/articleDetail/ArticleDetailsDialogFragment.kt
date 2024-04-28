package com.example.newsapp.core.presentation.fragments.articleDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.core.presentation.viewmodel.ArticleViewModel
import com.example.newsapp.core.utils.DateFormatter
import com.example.newsapp.databinding.FragmentArticleDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentArticleDetailBinding

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

        val articleViewModel =
            ViewModelProvider(owner = requireActivity())[ArticleViewModel::class.java]

        articleViewModel.article.observe(viewLifecycleOwner) { article ->
            binding.titleTextView.text = article.title
            binding.publishedAtTextView.text = getString(
                R.string.published_at,
                DateFormatter.formatDateTime(dateTime = article.publishedAt)
            )

            binding.descriptionTextView.apply {
                if (article.description.isNullOrEmpty()) visibility = View.GONE else text =
                    article.description
            }

            binding.authorTextView.apply {
                if (article.author.isNullOrEmpty()) visibility = View.GONE else text =
                    getString(R.string.by_author, article.author)
            }

            binding.contentTextView.apply {
                if (article.content.isNullOrEmpty()) visibility = View.GONE else text =
                    article.content
            }

            article.urlToImage?.let {
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