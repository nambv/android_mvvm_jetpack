package com.nambv.android_mvvm.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: UserId? = null,
    val gender: String = "",
    val name: Name? = null,
    val email: String = "",
    val phone: String = "",
    val cell: String = "",
)

@Serializable
data class UserId(
    val name: String = "",
    val value: String = "",
)

@Serializable
data class Name(
    val title: String = "",
    val first: String = "",
    val last: String = "",
)