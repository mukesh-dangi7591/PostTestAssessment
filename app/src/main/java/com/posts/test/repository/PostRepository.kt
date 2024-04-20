package com.posts.test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.posts.test.models.data_classes.Data
import com.posts.test.models.data_classes.UserPost
import com.posts.test.network_services.NetworkService
import com.posts.test.network_services.ResponseData

class PostRepository(private val networkService: NetworkService) {

    val userLivePosts = MutableLiveData<ResponseData<UserPost>>()

    val postList:LiveData<ResponseData<UserPost>>
        get() = userLivePosts

   // lateinit var result: retrofit2.Response<List<Data>>


   suspend fun getPostList(page: Int,limit: Int) {
       try {
           val result = networkService.getPostsList(page,limit)
          // println("errorMessage:- ${result.body()?.get(0)?.first_name}")
               if (result.body()!=null){
                 userLivePosts.postValue(ResponseData.Success(result.body()))
               }else{
                  userLivePosts.postValue(ResponseData.Error("Some error accure during api call"))
               }
       }catch (e:Exception){
           println("errorMessage:- ${e.message.toString()}")
          userLivePosts.postValue(ResponseData.Error(e.message.toString()))
       }

    }
}