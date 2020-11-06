package com.yongjaeMeal.cleanMeals.model

data class ScResponse(
    val status: Int?,
    val message: String?,
    val data: ScListData?
)

data class ScListData(val school: List<School>?)

data class School(
    val school_name: String?,
    val school_locate: String?,
    val office_code: String?,
    val school_id: String?
)