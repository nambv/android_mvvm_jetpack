package com.nambv.android_mvvm.data.repository

import com.nambv.android_mvvm.data.api.ApiService
import com.nambv.android_mvvm.data.model.UsersResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class UsersRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getUsers(page: Int, results: Int): UsersResult {
        return apiService.getUsers(page, results)
    }
}