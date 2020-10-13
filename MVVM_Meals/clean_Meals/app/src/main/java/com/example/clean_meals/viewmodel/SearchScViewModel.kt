package com.example.clean_meals.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean_meals.model.ScResponse
import com.example.clean_meals.network.ScListAPI
import com.example.clean_meals.network.ScListClient
import com.example.clean_meals.view.SearchScActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SearchScViewModel : ViewModel(){

    lateinit var ScAPI : ScListAPI
    var ScClient : Retrofit
    var nullSearch = MutableLiveData<String>()
    init {
        ScClient = ScListClient.getInstance()
        nullSearch.value = "검색 결과가 없습니다."


    }

    private fun getSchool(){
        ScAPI = ScClient.create(ScListAPI::class.java)
        ScAPI.SearchSc("대구소프트웨어고등학교").enqueue(object : Callback<ScResponse> {

            override fun onResponse(call: Call<ScResponse>, response: Response<ScResponse>) {
                val Response = response.body()
                Log.d("Sucess", Response.toString())
                if (Response != null) {
                    if (Response.status == 200) {
                        //값이 정상적으로 온다면 실행
                    } else {

                    }
                }
            }

            override fun onFailure(call: Call<ScResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}