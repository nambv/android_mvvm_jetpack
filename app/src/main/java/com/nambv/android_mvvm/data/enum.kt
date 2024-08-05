package com.nambv.android_mvvm.data

sealed class ViewState {
    object Loading : ViewState()
    object Success : ViewState()
    data class Error(val message: String) : ViewState()
}