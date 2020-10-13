package com.example.clean_meals.model

data class ScResponse(
    val status: Int,
    val message: String,
    val data: Data?
)

data class Data(
    val sc_list: List<School?>?
)

data class School(
    val school_name: String?,
    val office_code: String?,
    val school_id: String?,
    val school_locate: String?
)