package com.example.schoolmeals.API

import com.example.schoolmeals.Responses.ScResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ScListAPI {
    @GET("school")
    fun SearchSc(@Query("name") name : String): Call<ScResponse>
}