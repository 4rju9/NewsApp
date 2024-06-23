package app.netlify.dev4rju9.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import app.netlify.dev4rju9.newsapp.databinding.ActivityNewsBinding
import app.netlify.dev4rju9.newsapp.db.ArticleDatabse
import app.netlify.dev4rju9.newsapp.repository.NewsRepository

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = NewsRepository(ArticleDatabse(this))
        val factory = NewsViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        WindowCompat.setDecorFitsSystemWindows(window, false)
        val binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView
            .setupWithNavController(binding.flFragment.getChildAt(0).findNavController())

    }
}