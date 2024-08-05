package com.nambv.android_mvvm.ui.view.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nambv.android_mvvm.data.model.User
import com.nambv.android_mvvm.data.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: UsersRepository) : ViewModel() {
    private val _items = MutableLiveData<List<User>>()
    val items: MutableLiveData<List<User>> get() = _items

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    private var currentPage = 1
    private val pageSize = 20

    init {
        fetchItems()
    }

    fun fetchItems() {
        try {
            viewModelScope.launch {
                val newUsers = repository.getUsers(currentPage, pageSize).users
                _items.value = _items.value.orEmpty() + newUsers
                currentPage++
            }
        } catch (e: Exception) {
            _errorMessage.value = "Failed to fetch items: ${e.message}"
        }
    }

    fun refreshItems() {
        viewModelScope.launch {
            _isRefreshing.value = true

            currentPage = 1
            val newUsers = repository.getUsers(currentPage, pageSize).users

            _items.value = newUsers
            _isRefreshing.value = false
        }
    }
}