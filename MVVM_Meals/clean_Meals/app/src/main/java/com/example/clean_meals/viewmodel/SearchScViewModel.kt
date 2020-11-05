package com.example.clean_meals.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean_meals.model.MealsResponse
import com.example.clean_meals.model.ScResponse
import com.example.clean_meals.model.School
import com.example.clean_meals.network.GetDataAPI
import com.example.clean_meals.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SearchScViewModel : ViewModel(){

    var createAdapter = MutableLiveData<Boolean>()
    var nullSearch = "검색 결과가 없습니다."
    var statusValue = MutableLiveData<Int>()
    var schoolList = ArrayList<School>()
    var et_School = ""
    var ScName = ""

    private fun getSchool(){
        val call : Call<ScResponse> = RetrofitClient.instance.GetData.SearchSc(et_School)
        call.enqueue(object : Callback<ScResponse> {
            override fun onResponse(call: Call<ScResponse>, response: Response<ScResponse>) {
                val Response = response.body()
                if (Response != null) {
                    if (Response.status == 200) {
                        //값이 정상적으로 온다면 실행
                        Log.d("Success", Response.toString())
                        schoolList = Response.data?.school as ArrayList<School>
                        nullSearch = ""
                        statusValue.value = Response.status
                        createAdapter.value = true
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
    fun searchScClcik(){
        Log.d("sad","adsdas")
        getSchool()
    }
}