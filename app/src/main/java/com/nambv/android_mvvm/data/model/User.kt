package com.nambv.android_mvvm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersResult(
    @SerialName("results") val users: List<User>
)

@Serializable
data class User(
    val id: UserId? = null,
    val gender: String = "",
    val name: Name? = null,
    val email: String = "",
    val phone: String = "",
    val cell: String = "",
    val picture: Picture? = null,
)

@Serializable
data class UserId(
    val name: String = "",
    val value: String? = null,
)

@Serializable
data class Name(
    val title: String = "",
    val first: String = "",
    val last: String = "",
)

@Serializable
data class Picture(
    val thumbnail: String = ""
)