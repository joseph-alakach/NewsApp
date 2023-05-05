package com.example.newsapp

import retrofit2.Response

class NewsRepo( ) {
    suspend fun fetchNews( country: String, apiKey: String): Response<NewsResponse>{
        return RetrofitHelper.getInstance().create(NewsApiService::class.java).fetchNews(country,apiKey)
    }
    suspend fun fetchNewsCategories( country: String, apiKey: String, category: String): Response<NewsResponse>{
        return RetrofitHelper.getInstance().create(NewsApiService::class.java).fetchNews(country,apiKey, category= category)
    }
    suspend fun fetchNewsSearch( country: String, apiKey: String, query: String): Response<NewsResponse>{
        return RetrofitHelper.getInstance().create(NewsApiService::class.java).fetchNews(country,apiKey, query= query)
    }


}