package com.dikascode.beltexamtwo.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Employment(
    @SerializedName("key_skill")
    val keySkill: String,
    @SerializedName("title")
    val title: String
): Serializable