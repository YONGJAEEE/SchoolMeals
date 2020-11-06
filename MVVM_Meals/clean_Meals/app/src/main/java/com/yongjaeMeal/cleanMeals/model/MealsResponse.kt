package com.yongjaeMeal.cleanMeals.model

data class MealsResponse(
    val status : Int?,
    val message : String?,
    val data : MealsData?
)