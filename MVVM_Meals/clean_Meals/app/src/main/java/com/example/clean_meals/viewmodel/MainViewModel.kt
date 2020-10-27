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
    var schoolName = MyApplication.prefs.getString("schoolName", "null")
    var officeCode = MyApplication.prefs.getString("officeCode", "null")
    var schoolId = MyApplication.prefs.getString("schoolId", "null")
    var schoolChageValue = MutableLiveData<String>()

    var date: LocalDate = LocalDate.now()
    var liveDate = MutableLiveData(date.toString())

    init {
        getMeals(date.toString())
    }

    fun beforeDay(){
        date = date.minusDays(1)
        getMeals(date.toString())
        liveDate.value = date.toString()

    }
    fun nextDay(){
        date = date.plusDays(1)
        getMeals(date.toString())
        liveDate.value = date.toString()
    }
    fun schoolChange(){
        schoolChageValue.value = "ok"
    }

    private fun getMeals(dateString: String) {
        val call: Call<MealsResponse> =
            RetrofitClient.instance.GetData.GetMeals(schoolId, officeCode, dateString)
        call.enqueue(object : Callback<MealsResponse> {
            override fun onResponse(call: Call<MealsResponse>, response: Response<MealsResponse>) {
                val responseData = response.body()

                val meal = Meal(
                    null,
                    null,
                    null
                )
                Log.d("TAG",responseData.toString())
                if (responseData?.data != null) {
                when (responseData.data.meal?.size) {
                    1 -> {
                        meal.breakfast = responseData.data.meal[0].replace("<br/>", "\n\n")
                        meal.lunch = null
                        meal.dinner = null
                    }
                    2 -> {
                        meal.breakfast = response.body()!!.data?.meal?.get(0)?.replace("<br/>", "\n\n")
                        meal.lunch = responseData.data.meal[1].replace("<br/>", "\n\n")
                        meal.dinner = null
                    }
                    3 -> {
                        meal.breakfast = response.body()!!.data?.meal?.get(0)?.replace("<br/>", "\n\n")
                        meal.lunch = response.body()!!.data?.meal?.get(1)?.replace("<br/>", "\n\n")
                        meal.dinner = responseData.data.meal[2].replace("<br/>", "\n\n")
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
                Log.d("Success", meal.toString())
                DataUtil.meal.value = meal
            }
            }

            override fun onFailure(call: Call<MealsResponse>, t: Throwable) {
                Log.d("Fail", t.toString())
            }
        })
    }
}