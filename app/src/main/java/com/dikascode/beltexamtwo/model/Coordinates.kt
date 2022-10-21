package com.dikascode.beltexamtwo.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Coordinates(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
): Serializable