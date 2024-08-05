package com.nambv.android_mvvm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CarSearchResponseItem(
    @SerialName("colour") val colour: String? = null,
    @SerialName("description") val description: String = "",
    @SerialName("firstRegistration") val firstRegistration: String?? = null,
    @SerialName("fuel") val fuel: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("make") val make: String = "",
    @SerialName("mileage") val mileage: Int? = null,
    @SerialName("model") val model: String = "",
    @SerialName("modelline") val modelline: String = "",
    @SerialName("price") val price: Int = 0,
    @SerialName("seller") val seller: Seller? = null,
    @SerialName("images") val images: List<Image>? = null,
)

@Serializable
data class Image(
    @SerialName("url") val url: String? = null,
)

@Serializable
data class Seller(
    @SerialName("city") val city: String? = null,
    @SerialName("phone") val phone: String? = null,
    @SerialName("type") val type: String? = null,
)