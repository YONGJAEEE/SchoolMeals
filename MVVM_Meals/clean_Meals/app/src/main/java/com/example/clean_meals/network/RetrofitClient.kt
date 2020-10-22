package com.example.clean_meals.network

import com.example.clean_meals.model.MealsResponse
import com.example.clean_meals.model.ScResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private var instance: Retrofit? = null

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://34.64.101.114/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val GetData : GetDataAPI = retrofit.create(GetDataAPI::class.java)

    companion object {
        val instance = RetrofitClient()
    }
}