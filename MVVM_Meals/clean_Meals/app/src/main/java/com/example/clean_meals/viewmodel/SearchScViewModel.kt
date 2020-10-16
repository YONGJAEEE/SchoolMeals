package com.example.clean_meals.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean_meals.model.ScResponse
import com.example.clean_meals.model.School
import com.example.clean_meals.network.ScListAPI
import com.example.clean_meals.network.ScListClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SearchScViewModel : ViewModel(){

    lateinit var ScAPI : ScListAPI
    var ScClient : Retrofit
    var finishValue = MutableLiveData<Boolean>()
    var nullSearch = MutableLiveData<String>()
    var statusValue = MutableLiveData<Int>()
    var schoolList = ArrayList<School>()
    var et_School = ""
    var ScName = ""

    init {
        ScClient = ScListClient.getInstance()
        nullSearch.value = "검색 결과가 없습니다."
    }

    private fun getSchool(){
        ScAPI = ScClient.create(ScListAPI::class.java)
        ScAPI.SearchSc(et_School).enqueue(object : Callback<ScResponse> {
            override fun onResponse(call: Call<ScResponse>, response: Response<ScResponse>) {
                val Response = response.body()
                if (Response != null) {
                    if (Response.status == 200) {
                        //값이 정상적으로 온다면 실행
                        Log.d("Sucess", Response.toString())
                        schoolList = Response.data?.school as ArrayList<School>
                        nullSearch.value = ""
                        statusValue.value = Response.status
                        finishValue.value = true
                    } else {
                        Log.d("fail",Response.toString())
                        statusValue.value = Response.status
                    }
                }

                else{
                    statusValue.value = Response?.status
                }
            }

            override fun onFailure(call: Call<ScResponse>, t: Throwable) {
                Log.d("retrofit fail", t.toString())
            }
        })
    }
    fun SearchScClcik(){
            getSchool()
    }
}