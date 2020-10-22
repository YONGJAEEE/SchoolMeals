package com.example.clean_meals.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean_meals.model.Meal
import com.example.clean_meals.model.MealsResponse
import com.example.clean_meals.network.RetrofitClient
import com.example.clean_meals.widget.DataUtil
import com.example.clean_meals.widget.MyApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel : ViewModel() {
    var SchoolName = MyApplication.prefs.getString("schoolName", "null")
    var OfficeCode = MyApplication.prefs.getString("officeCode", "null")
    var SchoolId = MyApplication.prefs.getString("schoolId", "null")


    var Date: LocalDate = LocalDate.now()

    init {
      getMeals("2020-10-21")
    }

    fun getMeals( Date: String ) {
        val call: Call<MealsResponse> =
            RetrofitClient.instance.GetData.GetMeals(SchoolId, OfficeCode, Date)
        call.enqueue(object : Callback<MealsResponse> {
            override fun onResponse(call: Call<MealsResponse>, response: Response<MealsResponse>) {
                val responseData = response.body()
                val meal = Meal(
                    responseData?.data?.meal?.get(0).toString().replace("<br/>", "\n\n"),
                    responseData?.data?.meal?.get(1).toString().replace("<br/>", "\n\n"),
                    responseData?.data?.meal?.get(2).toString().replace("<br/>", "\n\n")
                )
                DataUtil.meal.value = meal
            }
            override fun onFailure(call: Call<MealsResponse>, t: Throwable) {

            }
        })
    }
}