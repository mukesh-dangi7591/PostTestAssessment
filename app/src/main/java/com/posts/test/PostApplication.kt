package com.posts.test

import android.app.Application
import com.posts.test.network_services.NetworkService
import com.posts.test.network_services.RetrofitHelper
import com.posts.test.repository.PostRepository


class PostApplication : Application() {
    lateinit var postRepository: PostRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize(){
         val apiService: NetworkService = RetrofitHelper.networService
        postRepository = PostRepository(apiService)
    }
}