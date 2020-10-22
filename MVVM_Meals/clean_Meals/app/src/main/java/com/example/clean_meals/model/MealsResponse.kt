package com.example.clean_meals.model

data class MealsResponse(
    val status : Int?,
    val message : String?,
    val data : MealsData?
)