package app.netlify.dev4rju9.newsapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.netlify.dev4rju9.newsapp.R
import app.netlify.dev4rju9.newsapp.adapters.NewsAdapter
import app.netlify.dev4rju9.newsapp.databinding.FragmentSearchNewsBinding
import app.netlify.dev4rju9.newsapp.ui.NewsActivity
import app.netlify.dev4rju9.newsapp.ui.NewsViewModel
import app.netlify.dev4rju9.newsapp.util.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import app.netlify.dev4rju9.newsapp.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var binding: FragmentSearchNewsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchNewsBinding.bind(view)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        var job: Job? = null
        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> showProgressBar()
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsAdapter.differ.submitList(it.articles) }
                }
            }
        }
    }

    private fun hideProgressBar () {
        binding.paginationProgressBar.visibility = View.GONE
    }

    private fun showProgressBar () {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView () {
        newsAdapter = NewsAdapter()
        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}