package com.example.clean_meals.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ScListClient {
    private var instance: Retrofit? = null

    fun getInstance(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl("http://34.64.101.114/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}