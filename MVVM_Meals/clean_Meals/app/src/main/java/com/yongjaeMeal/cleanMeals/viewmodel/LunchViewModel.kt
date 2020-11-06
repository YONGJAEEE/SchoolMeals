package com.yongjaeMeal.cleanMeals.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LunchViewModel : ViewModel() {
    val lunch = MutableLiveData<String>()
    init {
    }
}