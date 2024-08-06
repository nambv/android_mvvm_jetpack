package com.nambv.android_mvvm.data.remote.dto

import com.nambv.android_mvvm.data.model.Name
import com.nambv.android_mvvm.data.model.Picture
import com.nambv.android_mvvm.data.model.User
import com.nambv.android_mvvm.data.model.UserId
import com.nambv.android_mvvm.data.model.UsersResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UsersResultDto(
    @SerialName("results") val users: List<UserDto>
) {
    fun toParcelable(): UsersResult {
        val data = users.map {
            it.toParcelable()
        }

        return UsersResult(data)
    }
}

@Serializable
data class UserDto(
    val id: UserIdDto? = null,
    val gender: String = "",
    val name: NameDto? = null,
    val email: String = "",
    val phone: String = "",
    val cell: String = "",
    val picture: PictureDto? = null,
) {
    fun toParcelable(): User {
        return User(
            id = id?.toParcelable(),
            gender = gender,
            name = name?.toParcelable(),
            email = email,
            phone = phone,
            cell = cell,
            picture = picture?.toParcelable()
        )
    }
}

@Serializable
data class UserIdDto(
    val name: String = "",
    val value: String? = null,
) {
    fun toParcelable(): UserId = UserId(
        name = name, value = value
    )
}

@Serializable
data class NameDto(
    val title: String = "",
    val first: String = "",
    val last: String = "",
) {
    fun toParcelable(): Name = Name(
        title = title,
        first = first,
        last = last,
    )
}

@Serializable
data class PictureDto(
    val large: String = "", val medium: String = "", val thumbnail: String = ""
) {
    fun toParcelable(): Picture = Picture(
        large, medium, thumbnail
    )
}