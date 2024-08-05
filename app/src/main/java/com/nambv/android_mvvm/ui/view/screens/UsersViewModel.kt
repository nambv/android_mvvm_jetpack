package com.nambv.android_mvvm.ui.view.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nambv.android_mvvm.data.ViewState
import com.nambv.android_mvvm.data.model.User
import com.nambv.android_mvvm.data.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: UsersRepository) : ViewModel() {
    private val _state = MutableStateFlow<ViewState>(ViewState.Loading)
    val state: StateFlow<ViewState> get() = _state

    private val _items = MutableStateFlow<List<User>>(emptyList())
    val items: StateFlow<List<User>> get() = _items

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    private var currentPage = 1
    private val pageSize = 20

    init {
        fetchItems()
    }

    fun fetchItems() {
        _state.value = ViewState.Loading

        try {
            viewModelScope.launch {
                val newUsers = repository.getUsers(currentPage, pageSize).users
                _items.value = _items.value + newUsers
                currentPage++
            }

            _state.value = ViewState.Success
        } catch (e: Exception) {
            _state.value = ViewState.Error("Failed to fetch items: ${e.message}")
        }
    }

    fun refreshItems() {
        _state.value = ViewState.Loading
        try {
            viewModelScope.launch {
                _isRefreshing.value = true
                currentPage = 1

                val newUsers = repository.getUsers(currentPage, pageSize).users
                _items.value = newUsers
            }
            _state.value = ViewState.Success
        } catch (e: Exception) {
            _state.value = ViewState.Error("Failed to refresh items: ${e.message}")
        } finally {
            _isRefreshing.value = false
        }
    }
}