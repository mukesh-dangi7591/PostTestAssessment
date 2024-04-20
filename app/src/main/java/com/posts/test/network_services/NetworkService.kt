package com.posts.test.network_services

import com.posts.test.models.data_classes.Data
import com.posts.test.models.data_classes.UserPost
import com.posts.test.utils.AppApiUrl.posts_url1
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

   @GET(posts_url1)
  suspend fun getPostsList( @Query("page") page: Int,
                            @Query("per_page") limit: Int
  ): Response<UserPost>

}