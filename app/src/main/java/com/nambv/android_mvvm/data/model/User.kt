package com.nambv.android_mvvm.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class UsersResult(
    @SerialName("results") val users: List<User>
) : Parcelable

@Parcelize
@Serializable
data class User(
    val id: UserId? = null,
    val gender: String = "",
    val name: Name? = null,
    val email: String = "",
    val phone: String = "",
    val cell: String = "",
    val picture: Picture? = null,
) : Parcelable

@Parcelize
@Serializable
data class UserId(
    val name: String = "",
    val value: String? = null,
) : Parcelable

@Parcelize
@Serializable
data class Name(
    val title: String = "",
    val first: String = "",
    val last: String = "",
) : Parcelable

@Parcelize
@Serializable
data class Picture(
    val large: String = "", val medium: String = "", val thumbnail: String = ""
) : Parcelable