package com.posts.test.view_models.view_model_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.posts.test.repository.PostRepository
import com.posts.test.view_models.PostsViewModel

class PostViewModelFactory(private val postRepository: PostRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostsViewModel(postRepository) as T
    }
}