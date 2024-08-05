package com.nambv.android_mvvm.ui.view.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nambv.android_mvvm.data.model.User
import com.nambv.android_mvvm.data.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: UsersRepository) :
    ViewModel() {
    private val _items = MutableStateFlow<List<User>>(emptyList())
    val items: StateFlow<List<User>> get() = _items

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private var currentPage = 1
    private val pageSize = 20

    init {
        fetchItems()
    }

    fun fetchItems() {
        try {
            viewModelScope.launch {
                val newUsers = repository.getUsers(currentPage, pageSize).users
                _items.value = _items.value + newUsers
                currentPage++
            }
        } catch (e: Exception) {
            _errorMessage.value = "Failed to fetch items: ${e.message}"
        }
    }

    fun refreshItems() {
        try {
            viewModelScope.launch {
                _isRefreshing.value = true
                currentPage = 1

                val newUsers = repository.getUsers(currentPage, pageSize).users
                _items.value = newUsers
            }
        } catch (e: Exception) {
            _errorMessage.value = "Failed to refresh items: ${e.message}"
        } finally {
            _isRefreshing.value = false
        }
    }
}