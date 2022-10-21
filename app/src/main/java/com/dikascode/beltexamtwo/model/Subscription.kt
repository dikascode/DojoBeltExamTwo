package com.dikascode.beltexamtwo.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Subscription(
    @SerializedName("payment_method")
    val paymentMethod: String,
    @SerializedName("plan")
    val plan: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("term")
    val term: String
): Serializable