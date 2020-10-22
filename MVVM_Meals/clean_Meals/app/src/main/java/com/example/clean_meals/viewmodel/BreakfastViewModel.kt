package com.example.clean_meals.viewmodel

import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean_meals.model.MealsResponse
import com.example.clean_meals.network.RetrofitClient
import com.example.clean_meals.widget.DataUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreakfastViewModel : ViewModel() {

    val breakfast = MutableLiveData<String>()
    init {

    }
}