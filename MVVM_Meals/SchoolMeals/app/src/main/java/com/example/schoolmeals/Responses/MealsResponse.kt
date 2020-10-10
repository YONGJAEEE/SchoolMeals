package com.example.schoolmeals.Responses

data class MealsResponse(
    val time: String?,
    val breakfast: Breakfast?,
    val lunch: Lunch?,
    val dinner: Dinner?
)

data class Breakfast(
    val lists: String?,
    val cal: String?,
    val NTR: String?
)

data class Dinner(
    val lists: String?,
    val cal: String?,
    val NTR: String?
)

data class Lunch(
    val lists: String?,
    val cal: String?,
    val NTR: String?
)