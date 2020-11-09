package com.yongjaeMeal.cleanMeals.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yongjaeMeal.cleanMeals.model.Meal
import com.yongjaeMeal.cleanMeals.model.MealsResponse
import com.yongjaeMeal.cleanMeals.network.RetrofitClient
import com.yongjaeMeal.cleanMeals.widget.DataUtil
import com.yongjaeMeal.cleanMeals.widget.MyApplication
import com.yongjaeMeal.cleanMeals.widget.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel : ViewModel() {
    var schoolName = MyApplication.prefs.getString("schoolName", "null")
    var officeCode = MyApplication.prefs.getString("officeCode", "null")
    var schoolId = MyApplication.prefs.getString("schoolId", "null")

    val button = SingleLiveEvent<Unit>()

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
                if (responseData?.status == 200) {
                    when (responseData.data?.meal?.size) {
                        1 -> {
                            meal.breakfast = responseData.data.meal[0].replace("<br/>", "\n\n")
                            meal.lunch = null
                            meal.dinner = null
                        }
                        2 -> {
                            meal.breakfast =
                                response.body()!!.data?.meal?.get(0)?.replace("<br/>", "\n\n")
                            meal.lunch = responseData.data.meal[1].replace("<br/>", "\n\n")
                            meal.dinner = null
                        }
                        3 -> {
                            meal.breakfast =
                                response.body()!!.data?.meal?.get(0)?.replace("<br/>", "\n\n")
                            meal.lunch =
                                response.body()!!.data?.meal?.get(1)?.replace("<br/>", "\n\n")
                            meal.dinner = responseData.data.meal[2].replace("<br/>", "\n\n")
                        }
                    }
                    Log.d("Success", meal.toString())
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

    fun buttonCall(){
        button.call()
    }
}