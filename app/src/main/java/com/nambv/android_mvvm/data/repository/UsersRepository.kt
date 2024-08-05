package com.nambv.android_mvvm.data.repository

import com.nambv.android_mvvm.data.api.ApiService
import com.nambv.android_mvvm.data.model.User
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class UsersRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getUsers(page: Int, results: Int): List<User> {
        return apiService.getUsers(page, results)
    }
}