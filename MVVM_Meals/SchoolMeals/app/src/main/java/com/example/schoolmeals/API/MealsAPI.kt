package com.example.schoolmeals.API

import com.example.schoolmeals.Responses.MealsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsAPI {
    @GET("meals")
    fun CallMeals(
        @Query("AD_CODE")
        AD_CODE : String,
        @Query("SC_CODE")
        SC_CODE: String,
        @Query("WHEN")
        WHEN : String
    ): Call<MealsResponse>
}