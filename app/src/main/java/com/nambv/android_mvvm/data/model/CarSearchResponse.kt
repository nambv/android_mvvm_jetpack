package com.nambv.android_mvvm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CarSearchResponseItem(
    @SerialName("colour")
    val colour: String?,
    @SerialName("description")
    val description: String,
    @SerialName("firstRegistration")
    val firstRegistration: String?,
    @SerialName("fuel")
    val fuel: String,
    @SerialName("id")
    val id: Int,
    @SerialName("make")
    val make: String,
    @SerialName("mileage")
    val mileage: Int?,
    @SerialName("model")
    val model: String,
    @SerialName("modelline")
    val modelline: String,
    @SerialName("price")
    val price: Int,
)