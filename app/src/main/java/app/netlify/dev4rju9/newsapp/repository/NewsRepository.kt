package app.netlify.dev4rju9.newsapp.repository

import app.netlify.dev4rju9.newsapp.api.RetrofitInstance
import app.netlify.dev4rju9.newsapp.db.ArticleDatabse

class NewsRepository (
    val db: ArticleDatabse
) {
    suspend fun getBreakingNews (countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
    suspend fun searchNews (searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)
}