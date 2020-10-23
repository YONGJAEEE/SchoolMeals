package com.example.clean_meals.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
        getMeals("2020-10-23")
    }

    fun getMeals(Date: String) {
        val call: Call<MealsResponse> =
            RetrofitClient.instance.GetData.GetMeals(SchoolId, OfficeCode, Date)
        call.enqueue(object : Callback<MealsResponse> {
            override fun onResponse(call: Call<MealsResponse>, response: Response<MealsResponse>) {
                val responseData = response.body()
                val meal = Meal(
                    null,
                    null,
                    null
                )
                for (i in 0..2){
                    when(i){
                        0 -> meal.breakfast = responseData?.data?.meal?.get(i)
                        1 -> meal.lunch = responseData?.data?.meal?.get(i)
                        2 -> meal.dinner = responseData?.data?.meal?.get(i)
                    }
                }
                if (meal.breakfast == null) {
                    meal.breakfast = "급식이 없습니다."
                }
                if (meal.lunch == null) {
                    meal.lunch = "급식이 없습니다."
                }
                if (meal.dinner == null) {
                    meal.dinner = "급식이 없습니다."
                }
                DataUtil.meal.value = meal
            }

            override fun onFailure(call: Call<MealsResponse>, t: Throwable) {
                Log.d("Fail", t.toString())
            }
        })
    }
}