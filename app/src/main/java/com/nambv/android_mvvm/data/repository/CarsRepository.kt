package com.nambv.android_mvvm.data.repository

import com.nambv.android_mvvm.data.api.ApiService
import com.nambv.android_mvvm.data.model.CarSearchResponseItem
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class CarsRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getCars(): List<CarSearchResponseItem> {
        return apiService.getCars()
    }
}