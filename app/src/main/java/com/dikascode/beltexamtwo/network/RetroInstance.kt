package com.dikascode.beltexamtwo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object{
        private const val BASE_URL = "https://random-data-api.com/api/v2/"

        fun getRetroInstance(): Retrofit {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}