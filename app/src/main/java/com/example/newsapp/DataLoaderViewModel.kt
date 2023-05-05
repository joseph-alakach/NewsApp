package com.example.newsapp


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class DataLoaderViewModel : ViewModel() {

    private val _posts = MutableLiveData<Result<Response<NewsResponse>>>()
    val posts: LiveData<Result<Response<NewsResponse>>> = _posts

    fun loadNews() {

        viewModelScope.launch {
            try {

                val response = NewsRepo().fetchNews("us","f4fe74395e044719a5587735000a1b1e")
                _posts.postValue(Result.success(response))
            } catch (e: Exception) {
                _posts.postValue(Result.error(e))
            }
        }
    }
    fun loadNewsWithCategories(category: String) {

        viewModelScope.launch {
            try {

                val response = NewsRepo().fetchNewsCategories("us","f4fe74395e044719a5587735000a1b1e", category.lowercase())
                _posts.postValue(Result.success(response))
            } catch (e: Exception) {
                _posts.postValue(Result.error(e))
            }
        }
    }
    fun search(query: String) {

        viewModelScope.launch {
            try {

                val response = NewsRepo().fetchNewsSearch("us","f4fe74395e044719a5587735000a1b1e", query.lowercase())
                _posts.postValue(Result.success(response))
            } catch (e: Exception) {
                _posts.postValue(Result.error(e))
            }
        }
    }

}

sealed class Result<out T : Any> {
    data class Loading(val message: String = "") : Result<Nothing>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    companion object {
        fun <T : Any> loading(message: String = ""): Result<T> = Loading(message)
        fun <T : Any> success(data: T): Result<T> = Success(data)
        fun error(exception: Exception): Result<Nothing> = Error(exception)
    }
}