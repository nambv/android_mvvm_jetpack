package com.nambv.android_mvvm.data.api

import com.nambv.android_mvvm.data.model.UsersResult
import retrofit2.http.*

interface ApiService {

    @GET("/api")
    suspend fun getUsers(
        @Query("page") page: Int, @Query("results") results: Int
    ): UsersResult
}
