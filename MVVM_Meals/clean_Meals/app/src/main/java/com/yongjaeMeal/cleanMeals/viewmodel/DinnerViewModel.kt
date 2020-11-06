package com.yongjaeMeal.cleanMeals.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DinnerViewModel : ViewModel() {
    val dinner = MutableLiveData<String>()
    init {
    }
}