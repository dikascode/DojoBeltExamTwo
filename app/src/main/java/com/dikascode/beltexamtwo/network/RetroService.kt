package com.dikascode.beltexamtwo.network

import com.dikascode.beltexamtwo.model.UserData
import retrofit2.Call
import retrofit2.http.GET

interface RetroService {

    /*
    This returns an array of 20 user data. It could be made dynamic to return a number defined by on the front-end or otherwise
    A variable can be passed in as query instead
     */

    @GET("users?size=20")
    fun getRandomUserData(): Call<UserData>
}