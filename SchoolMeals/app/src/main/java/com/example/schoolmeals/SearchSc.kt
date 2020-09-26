package com.example.schoolmeals

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchSc {

    fun getSchool(): ScListAPI = retrofit.create(ScListAPI::class.java)

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://kyj-school-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}