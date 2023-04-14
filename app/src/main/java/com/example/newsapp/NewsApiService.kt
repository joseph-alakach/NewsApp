package com.example.newsapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun fetchNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ):Response<NewsResponse>

}