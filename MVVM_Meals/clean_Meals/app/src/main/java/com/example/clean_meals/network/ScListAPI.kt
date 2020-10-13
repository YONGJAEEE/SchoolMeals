package com.example.clean_meals.network

import com.example.clean_meals.model.ScResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ScListAPI {
    @GET("search")
    fun SearchSc(@Query("school_name") name : String): Call<ScResponse>
}