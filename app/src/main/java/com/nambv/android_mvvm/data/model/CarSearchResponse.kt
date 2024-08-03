package com.nambv.android_mvvm.data.model

import kotlinx.serialization.Serializable

class CarSearchResponse : ArrayList<CarSearchResponseItem>()

@Serializable
data class CarSearchResponseItem(
    val colour: String?,
    val description: String,
    val firstRegistration: String?,
    val fuel: String,
    val id: Int,
    val images: List<Image>?,
    val make: String,
    val mileage: Int?,
    val model: String,
    val modelline: String,
    val price: Int,
    val seller: Seller?
)

@Serializable
data class Image(
    val url: String?
)

@Serializable
data class Seller(
    val city: String?,
    val phone: String?,
    val type: String?
)