package com.nambv.android_mvvm.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
