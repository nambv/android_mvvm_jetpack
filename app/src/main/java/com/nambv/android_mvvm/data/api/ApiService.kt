package com.nambv.android_mvvm.data.api

import com.nambv.android_mvvm.data.model.CarSearchResponseItem
import retrofit2.http.*

interface ApiService {

    @GET("/")
    suspend fun getCars(): List<CarSearchResponseItem>
}
