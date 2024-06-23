package app.netlify.dev4rju9.newsapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.netlify.dev4rju9.newsapp.R
import app.netlify.dev4rju9.newsapp.adapters.NewsAdapter
import app.netlify.dev4rju9.newsapp.databinding.FragmentBreakingNewsBinding
import app.netlify.dev4rju9.newsapp.ui.NewsActivity
import app.netlify.dev4rju9.newsapp.ui.NewsViewModel
import app.netlify.dev4rju9.newsapp.util.Resource

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var binding: FragmentBreakingNewsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBreakingNewsBinding.bind(view)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner) { response ->
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
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}