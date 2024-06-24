package app.netlify.dev4rju9.newsapp.api

import app.netlify.dev4rju9.newsapp.models.NewsResponse
import app.netlify.dev4rju9.newsapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "in",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>

    @GET("/v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("language")
        language: String = "hi",
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>

}