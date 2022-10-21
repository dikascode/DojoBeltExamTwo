package com.dikascode.beltexamtwo.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Address(
    @SerializedName("city")
    val city: String,
    @SerializedName("coordinates")
    val coordinates: Coordinates,
    @SerializedName("country")
    val country: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("street_address")
    val streetAddress: String,
    @SerializedName("street_name")
    val streetName: String,
    @SerializedName("zip_code")
    val zipCode: String
):Serializable