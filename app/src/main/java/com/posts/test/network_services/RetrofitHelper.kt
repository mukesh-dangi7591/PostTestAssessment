package com.posts.test.network_services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    //private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private const val BASE_URL = "https://acharyaprashant.org/"

    private val retrofitHelper: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val networService: NetworkService by lazy {
        retrofitHelper.create(NetworkService::class.java)
    }
}