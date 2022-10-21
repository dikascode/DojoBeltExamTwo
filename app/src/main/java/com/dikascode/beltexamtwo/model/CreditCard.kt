package com.dikascode.beltexamtwo.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CreditCard(
    @SerializedName("cc_number")
    val ccNumber: String
) : Serializable