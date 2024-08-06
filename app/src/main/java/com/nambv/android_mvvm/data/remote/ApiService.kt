package com.nambv.android_mvvm.data.remote

import com.nambv.android_mvvm.data.remote.dto.UsersResultDto
import retrofit2.http.*

interface ApiService {

    @GET("/api")
    suspend fun getUsers(
        @Query("page") page: Int, @Query("results") results: Int
    ): UsersResultDto
}
