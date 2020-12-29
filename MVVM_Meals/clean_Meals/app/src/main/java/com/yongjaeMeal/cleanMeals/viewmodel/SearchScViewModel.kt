package com.yongjaeMeal.cleanMeals.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yongjaeMeal.cleanMeals.model.ScResponse
import com.yongjaeMeal.cleanMeals.model.School
import com.yongjaeMeal.cleanMeals.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchScViewModel : ViewModel(){

    var createAdapter = MutableLiveData<Boolean>()
    var nullSearch = "검색 결과가 없습니다."
    var schoolList = ArrayList<School>()
    var et_School = ""
    var ScName = ""

    fun getSchool(){
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
                        createAdapter.value = true
                    } else {
                        Log.d("fail",Response.toString())
                    }
                }
            }

            override fun onFailure(call: Call<ScResponse>, t: Throwable) {
                Log.d("retrofit fail", t.toString())
            }
        })
    }
}