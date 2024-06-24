package app.netlify.dev4rju9.newsapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.netlify.dev4rju9.newsapp.repository.NewsRepository

@Suppress("UNCHECKED_CAST")
class NewsViewModelProviderFactory (
    val app: Application,
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app, newsRepository) as T
    }
}