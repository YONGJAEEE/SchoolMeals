package com.example.clean_meals.network

import com.example.clean_meals.model.MealsResponse
import com.example.clean_meals.model.ScResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataAPI {
    @GET("search")
    fun SearchSc(@Query("school_name") name: String): Call<ScResponse>

    @GET("meal")
    fun GetMeals(
        @Query("school_id") school_id: String,
        @Query("office_code") office_code: String,
        @Query("date") date: String
    ): Call<MealsResponse>

}