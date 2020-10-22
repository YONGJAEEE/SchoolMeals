package com.example.clean_meals.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean_meals.model.MealsResponse
import com.example.clean_meals.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LunchViewModel : ViewModel() {
    val lunch = MutableLiveData<String>()
    init {
    }
}