package com.nambv.android_mvvm.data.repository

import com.nambv.android_mvvm.data.model.UsersResult
import com.nambv.android_mvvm.data.remote.ApiService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class UsersRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getUsers(page: Int, results: Int): UsersResult {
        val dto = apiService.getUsers(page, results)
        return dto.toParcelable()
    }
}