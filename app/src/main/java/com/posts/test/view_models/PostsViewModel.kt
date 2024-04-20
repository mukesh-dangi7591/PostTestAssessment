package com.posts.test.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.posts.test.models.data_classes.Data
import com.posts.test.models.data_classes.UserPost
import com.posts.test.network_services.ResponseData
import com.posts.test.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostsViewModel(private val postRepository: PostRepository): ViewModel() {
    init {
      //  getPostsData(currentPostPage,pageSize)
    }
    val postsData: LiveData<ResponseData<UserPost>>
        get() = postRepository.postList

    fun getPostsData(page:Int,limit: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                postRepository.getPostList(page,limit)

                } catch (e: Exception) {

                }
            }

        }
    }
}