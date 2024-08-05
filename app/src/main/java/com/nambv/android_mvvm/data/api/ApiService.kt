package com.nambv.android_mvvm.data.api

import com.nambv.android_mvvm.data.model.User
import retrofit2.http.*

interface ApiService {

    @GET("/")
    suspend fun getUsers(
        @Query("page") page: Int, @Query("results") results: Int
    ): List<User>
}
