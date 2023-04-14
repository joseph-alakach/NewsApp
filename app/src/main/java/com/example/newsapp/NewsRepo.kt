package com.example.newsapp

import retrofit2.Response

class NewsRepo( ) {
    suspend fun fetchNews( country: String, apiKey: String): Response<NewsResponse>{
        return RetrofitHelper.getInstance().create(NewsApiService::class.java).fetchNews(country,apiKey)
    }


}